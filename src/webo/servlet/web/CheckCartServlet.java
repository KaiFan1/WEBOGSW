package webo.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.BookBean;
import webo.bean.CartBean;
import webo.bean.ShipBean;
import webo.dao.BookDAO;
import webo.dao.CartDAO;
import webo.dao.ShipDAO;

/**
 * Servlet implementation class CheckCartServlet
 */
@WebServlet("/CheckCartServlet")
public class CheckCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double totalprice = 0;
			int userid = Integer.valueOf(request.getParameter("useridforcart")).intValue();
			List<CartBean> cartlist = CartDAO.GetRecord(userid, 0);
			Map<CartBean, BookBean> map = new HashMap<CartBean, BookBean>();
			for (int i = 0; i < cartlist.size(); i++) {
				double temp = 0;
				// booklist.add(BookDAO.QueryBookForWeb(bookisbn));
				int quantity = cartlist.get(i).getBookQuantity();
				BookBean bean = BookDAO.QueryBookForWeb(cartlist.get(i).getBookISBN());
				double price = bean.getBookPrice();
				temp = quantity * price;
				System.out.println("The value you are looking at =" + bean);
				map.put(cartlist.get(i), bean);
				totalprice += temp;
			}
			DecimalFormat decimalFormat=new DecimalFormat("#.00");
			
			request.setAttribute("price", decimalFormat.format(totalprice));
			request.setAttribute("addedbook", map);
			request.getRequestDispatcher("cart.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("Register.jsp");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int userid = Integer.valueOf(request.getParameter("userid")).intValue();
			String shipadd = request.getParameter("shipAdd");
			int shipcode = Integer.valueOf(request.getParameter("shipCode")).intValue();
			String shipapt = request.getParameter("shipapt");
			System.out.println("shipadd" + shipadd);
			ShipBean shipBean = new ShipBean();
			shipBean.setUserID(userid);
			shipBean.setShippingAddress(shipadd);
			shipBean.setShippingZipCode(shipcode);
			shipBean.setShippingApartment(shipapt);
			int shipnum = ShipDAO.CreateNewShip(shipBean);
			PrintWriter printWriter = response.getWriter();
			printWriter.print(shipnum);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
