package webo.servlet.web;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.CartBean;
import webo.bean.InvoiceBean;
import webo.bean.PaymentBean;
import webo.bean.ShipBean;
import webo.dao.CartDAO;
import webo.dao.InvoiceDAO;
import webo.dao.PaymentDao;
import webo.dao.ShipDAO;

/**
 * Servlet implementation class InvoiceServlet
 */
@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userid = Integer.valueOf(request.getParameter("userid")).intValue();
		int shipnum = Integer.valueOf(request.getParameter("shipnum")).intValue();
		int invoiceid = (int) ((Math.random()*9+1)*100000);
		while (InvoiceDAO.QueryInvoiceID(invoiceid) != 0) {
			invoiceid = (int) ((Math.random()*9+1)*100000);	
		}
		double totalPrice = 0;
		String payadd = request.getParameter("payadd");
		String payapt = request.getParameter("payapt");
		int paycode = Integer.valueOf(request.getParameter("paycode")).intValue();
		int payphone = Integer.valueOf(request.getParameter("payphone")).intValue();
		PaymentBean bean = new PaymentBean();
		InvoiceBean invoiceBean = new InvoiceBean();
		try {
			
			bean.setUserID(userid);
			bean.setPaymentAddress(payadd);
			bean.setPaymentApartment(payapt);
			bean.setPaymentZipCode(paycode);
			bean.setPaymentPhone(payphone);
			PaymentDao.createNewRecord(bean);
			
			ShipBean shipBean = ShipDAO.QueryShipInfor(shipnum);
			//looking for the books from cart
			List<CartBean> list=CartDAO.QueryInvoiceInfor(userid, 0);
			for (int i = 0; i < list.size(); i++) {
				String isbn = list.get(i).getBookISBN();
				int quantity = list.get(i).getBookQuantity();
				double price = list.get(i).getBookPrice();
				
				invoiceBean.setShippingNUM(shipnum);
				invoiceBean.setCustomerID(userid);
				invoiceBean.setBookISBN(isbn);
				invoiceBean.setPrice(price*quantity);
				invoiceBean.setQuantity(quantity);
				invoiceBean.setTransID(invoiceid);
				InvoiceDAO.CreateInvoice(invoiceBean);
				totalPrice += price*quantity;
				System.out.println(list.get(i).getBookISBN()+"!"+list.get(i).getUserID());
				//clear the cart products
				CartDAO.DeleteRecord(list.get(i));
			}		
			DecimalFormat decimalFormat=new DecimalFormat("#.00");
			//export information to invoice.jsp
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = simpleDateFormat.format(new Date());
			request.setAttribute("invoiceID", invoiceid);
			request.setAttribute("invoicedate", date);
			request.setAttribute("shipaddress", shipBean.getShippingAddress());
			request.setAttribute("shipapt", shipBean.getShippingApartment());
			request.setAttribute("shipcode", shipBean.getShippingZipCode());
			request.setAttribute("booklist", list);
			request.setAttribute("totalprice", decimalFormat.format(totalPrice));
			request.setAttribute("tax", decimalFormat.format(totalPrice * 0.15));
			request.setAttribute("finalprice", decimalFormat.format(totalPrice+totalPrice * 0.15));
			request.getSession().setAttribute("productnum", 0);
			request.getRequestDispatcher("invoice.jsp").forward(request, response);
			System.out.println("THIS PURCHASE IS COMPLETED!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
