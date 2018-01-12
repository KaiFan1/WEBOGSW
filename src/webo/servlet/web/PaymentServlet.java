package webo.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.PaymentBean;
import webo.dao.PaymentDao;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
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
		try {
			int userid=Integer.valueOf(request.getParameter("userid")).intValue();
			String payadd=request.getParameter("payadd");
			int paycode=Integer.valueOf(request.getParameter("paycode")).intValue();
			String payapt=request.getParameter("payapt");
			int payphone=Integer.valueOf(request.getParameter("payphone")).intValue();
			PaymentBean paymentBean = new PaymentBean();
			paymentBean.setUserID(userid);
			paymentBean.setPaymentAddress(payadd);
			paymentBean.setPaymentApartment(payapt);
			paymentBean.setPaymentZipCode(paycode);
			paymentBean.setPaymentPhone(payphone);
			PaymentDao.createNewRecord(paymentBean);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
