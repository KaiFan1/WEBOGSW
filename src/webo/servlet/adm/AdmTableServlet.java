package webo.servlet.adm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.UserBean;
import webo.dao.UserDAO;

/**
 * Servlet implementation class AdmTableServlet
 */
@WebServlet("/AdmTableServlet")
public class AdmTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmTableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<UserBean> list = UserDAO.RetrieveUsers();
			String[] title ={"ID","First Name","Middle Name","Last Name","Birthday","Email","Username","Password"};
			request.setAttribute("tableforuser", list); 
			request.setAttribute("titleforuser", title);
			request.getRequestDispatcher("AdmUserLogged.jsp").forward(request, response);
			/*
			String json = new Gson().toJson(list);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
			*/
			System.out.println("Load userTable sucessfully!");
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
