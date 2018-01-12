package webo.servlet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import webo.bean.UserBean;
import webo.dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		try{
			UserBean user = new UserBean();
			int userid = (int) ((Math.random()*9+1)*100000);
			while (UserDAO.QueryUserRecord(userid) != null) {
				System.out.println("userid has been taken");
				userid = (int) ((Math.random()*9+1)*100000);	
			}
			System.out.println("userid is " + userid);
			user.setId(userid);
			user.setUsername(request.getParameter("username"));
			user.setFirstName(request.getParameter("firstName"));
			user.setMiddleName(request.getParameter("middleName"));
			user.setLastName(request.getParameter("lastName"));
			user.setBirthday(request.getParameter("birthday"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			UserDAO.Register(user);
			request.getSession().setAttribute("currentUser", user.getFirstName()+ " " + user.getLastName());	
			request.getSession().setAttribute("userid", user.getId());
			request.getSession().setAttribute("productnum", 0);
			response.sendRedirect("index.jsp");
		}
		catch(Exception ex){
			ex.getMessage();
		}
	}

}
