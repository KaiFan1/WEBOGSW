package webo.servlet.adm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webo.bean.BookBean;
import webo.dao.BookDAO;

/**
 * Servlet implementation class ChangeTableServlet
 */
@WebServlet("/ChangeBookTableServlet")
public class ChangeBookTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeBookTableServlet() {
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
			BookDAO.Delete(id);				
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
			BookBean bean = new BookBean();
			bean.setBookISBN(infor[0]);
			bean.setBookName(infor[1]);
			bean.setBookAuthFName(infor[2]);
			bean.setBookAuthMName(infor[3]);
			bean.setBookAuthLName(infor[4]);
			bean.setBookYear(infor[5]);
			bean.setPublishName(infor[6]);
			bean.setBookStock(Integer.valueOf(infor[7]).intValue());
			bean.setBookCat(infor[8]);
			bean.setBookPrice(Double.valueOf(infor[9]).doubleValue());
			String oldibsn = infor[10];
			BookDAO.UpdateForAdm(bean,oldibsn);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
