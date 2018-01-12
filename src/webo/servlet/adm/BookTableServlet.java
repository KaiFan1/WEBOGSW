package webo.servlet.adm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.BookBean;
import webo.dao.BookDAO;

/**
 * Servlet implementation class BookTableServlet
 */
@WebServlet("/BookTableServlet")
public class BookTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<BookBean> list = BookDAO.GetBookTable();
			String[] title ={"ISBN","Title","FirstName","MiddleName","LastName","Publish Year","Publisher","Stock","Catogroy","Price"};
			request.setAttribute("tableforbook", list); 
			request.setAttribute("titleforbook", title);
			request.setAttribute("display", "inline-block");
			request.getRequestDispatcher("AdmUserLogged.jsp").forward(request, response);
			/*
			String json = new Gson().toJson(list);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
			*/
			System.out.println("Load BookTable sucessfully!");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
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
