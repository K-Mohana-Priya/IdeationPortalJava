package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.ideabean;
import Beans.userbean;
import dao.ideadao;
import dao.notificationsdao;

/**
 * Servlet implementation class Reviewing
 */
public class Reviewing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reviewing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		HttpSession session=request.getSession();
		userbean user = (userbean)session.getAttribute("user");
		ideabean idea = (ideabean)session.getAttribute("idea");
		String user_id = user.getUser_id();
		String comment = request.getParameter("comment");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int idea_id = Integer.parseInt(request.getParameter("ideaid"));
		ideadao ida=new ideadao();
		ida.updateReviewIdea(user_id,idea_id,comment,rating,idea.getTitle(),idea.getUser_id());
		RequestDispatcher rd=null;
		rd = request.getRequestDispatcher("user_profile.jsp");
		rd.forward(request, response); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
