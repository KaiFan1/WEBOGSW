package webo.servlet.adm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.UserBean;
import webo.dao.UserDAO;

/**
 * Servlet implementation class ChangeUserTable
 */
@WebServlet("/ChangeUserTable")
public class ChangeUserTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("id");
			System.out.println("Starting to delete this record");
			UserDAO.Delete(id);				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String[] infor=request.getParameterValues("table[]");
			UserBean user = new UserBean();
			user.setId(Integer.valueOf(infor[0]).intValue());
			user.setFirstName(infor[1]);
			user.setMiddleName(infor[2]);
			user.setLastName(infor[3]);
			user.setBirthday(infor[4]);
			user.setEmail(infor[5]);
			user.setUsername(infor[6]);
			user.setPassword(infor[7]);
			UserDAO.Update(user);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
 	}

}
