package webo.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.BookBean;
import webo.dao.BookDAO;

/**
 * Servlet implementation class QueryBookServlet
 */
@WebServlet("/QueryBookServlet")
public class QueryBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query =request.getParameter("bookisbn");
		try {
			BookBean book =BookDAO.QueryBookForWeb(query);
			request.setAttribute("bookISBN", query);
			request.setAttribute("bookYear", book.getBookYear());
			request.setAttribute("bookPicRef", book.getBookPicRef());
			request.setAttribute("bookCat", book.getBookCat());
			request.setAttribute("bookName", book.getBookName());
			request.setAttribute("bookAuthorName", book.getBookAuthName());
			request.setAttribute("bookPrice", book.getBookPrice());
			request.getRequestDispatcher("book.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
