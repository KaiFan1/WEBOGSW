package webo.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import webo.bean.BookBean;
import webo.dao.BookDAO;
import webo.bean.CartBean;
import webo.bean.TempBean;
import webo.dao.CartDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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
		CartBean bean = new CartBean();
		BookBean bookBean = new BookBean();
		PrintWriter printWriter = response.getWriter();
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// String date = simpleDateFormat.format(new Date());
		String isbn = request.getParameter("isbn");
		int quantity = Integer.valueOf(request.getParameter("quantity")).intValue();
		int userid = Integer.valueOf(request.getParameter("userid")).intValue();
		System.out.println(isbn + " " + quantity + " " + userid);
		try {
			bookBean = BookDAO.QueryBookForWeb(isbn);
			if (bookBean.getBookStock() >= quantity) {
				bean.setUserID(userid);
				bean.setBookISBN(isbn);
				bean.setPurchaseFlage(0);// means user hasn't paied yet
				int q = CartDAO.QueryRecord(bean);
				System.out.println("The cart hase this book " + q);
				if (q != 0) {
					System.out.println("number of new q = " + quantity + " and the number of old q = " + q);
					bean.setBookQuantity(quantity + q);
					CartDAO.UpdateRecord(bean);
					
				}else{
					bean.setBookQuantity(quantity);
					CartDAO.CreateRecord(bean);
				}
				int pronum = CartDAO.GetRecord(bean.getUserID(), 0).size();
				request.getSession().setAttribute("productnum", pronum);
				printWriter.print(pronum);
			}else {
				printWriter.print("error");
			}
			printWriter.flush();
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
		try {
			String books = request.getParameter("test");
			System.out.println(books);
			Gson gson = new Gson();
			List<TempBean> list = gson.fromJson(books, new TypeToken<List<TempBean>>() {
			}.getType());
			CartBean cartBean = new CartBean();
			System.out.println("CART HAS " + list.size() + " items!");
			for (int i = 0; i < list.size(); i++) {
				int stock = BookDAO.QueryBookForWeb(list.get(i).getISBN()).getBookStock();
				int temp = list.get(i).getQ();
				System.out.println(list.get(i).getISBN() + " has " + stock + "in stock!");
				System.out.println(list.get(i).getISBN() + " is going to be brought " + temp);
				if (temp == 0) {
					continue;
				} else if (stock >= list.get(i).getQ()) {
					cartBean.setBookISBN(list.get(i).getISBN());
					cartBean.setBookQuantity(list.get(i).getQ());
					CartDAO.UpdateRecord(cartBean);
					BookDAO.UpdateBookStock(cartBean.getBookISBN(), stock-temp);
				} 
				//request.setCharacterEncoding("UTF-8");
				//response.getWriter().write(message);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
