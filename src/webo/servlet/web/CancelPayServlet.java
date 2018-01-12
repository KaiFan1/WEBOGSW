package webo.servlet.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.BookBean;
import webo.bean.CartBean;
import webo.dao.BookDAO;
import webo.dao.CartDAO;

/**
 * Servlet implementation class CancelPayServlet
 */
@WebServlet("/CancelPayServlet")
public class CancelPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelPayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String userid = request.getParameter("userid");
			List<CartBean> list = CartDAO.GetRecord(Integer.valueOf(userid).intValue(), 0);
			for (int i = 0; i < list.size(); i++) {
				String isbn = list.get(i).getBookISBN();
				int q = list.get(i).getBookQuantity();
				BookBean bean = BookDAO.QueryBookForWeb(isbn);
				BookDAO.UpdateBookStock(isbn, bean.getBookStock()+q);
			}
			response.sendRedirect("CheckCartServlet?useridforcart="+userid);;
		} catch (Exception e) {
			// TODO: handle exception
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
		doGet(request, response);
	}

}
