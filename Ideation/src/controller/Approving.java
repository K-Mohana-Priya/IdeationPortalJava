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
 * Servlet implementation class Approving
 */
public class Approving extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Approving() {
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
		ideabean idea = (ideabean)session.getAttribute("idea");
		String comment = request.getParameter("comment");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int idea_id = Integer.parseInt(request.getParameter("ideaid"));
		String status= request.getParameter("saveas");
		ideadao ida=new ideadao();
		String message=ida.updateIdea(idea_id,comment,rating,status,idea.getTitle(),idea.getUser_id());
        notificationsdao nda=new notificationsdao();
	    nda.insertNotification(idea.getUser_id(), message);
		RequestDispatcher rd=null;
		rd = request.getRequestDispatcher("user_profile.jsp");
		rd.forward(request, response); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
