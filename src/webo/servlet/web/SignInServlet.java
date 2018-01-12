package webo.servlet.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import webo.bean.CartBean;
import webo.bean.UserBean;
import webo.dao.CartDAO;
import webo.dao.UserDAO;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			UserBean bean = new UserBean();
			bean.setUsername(username);
			bean.setPassword(password);
			bean=UserDAO.login(bean);
			if(bean.isValid()){
				List<CartBean> list=CartDAO.GetRecord(bean.getId(), 0);
				System.out.println("this user has "+list.size()+ " in the cart!");
				Map<String, String> map = new HashMap<String,String>();
				map.put("username", bean.getUsername());
				map.put("userid", String.valueOf(bean.getId()));
				map.put("itemnum", String.valueOf(list.size()));
				System.out.println(list.size());
				response.setContentType("application/json");
				Gson gson =new Gson();
				PrintWriter printWriter = response.getWriter();
				request.getSession().setAttribute("currentUser", bean.getFirstName() + " " + bean.getLastName());	
				request.getSession().setAttribute("userid", bean.getId());
				request.getSession().setAttribute("productnum", list.size());
				printWriter.print(gson.toJson(map));
				printWriter.flush();
				System.out.println("You have logged in");
			}
			else{
				response.getWriter().write("false");
			}
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
