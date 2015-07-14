package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.ideabean;
import Beans.userbean;
import dao.IdeaDAO_1;
import dao.ideadao;
import dao.user_group_assodao;

/**
 * Servlet implementation class DisplayIdea
 */
public class DisplayIdea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayIdea() {
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
		HttpSession session = request.getSession();
		String ideaid = request.getParameter("selectedidea");
		System.out.println("ideaid:"+ideaid);
		RequestDispatcher rd=null;
		try{
		ideabean idea = new ideadao().getIdeabyUId(ideaid);
		session.setAttribute("idea",idea);
		System.out.println("ideaid by idea:"+idea.getIdea_id());
		String[] filenames = new ideadao().getIdeaAttachmentbyId(Integer.parseInt(ideaid)); 
		session.setAttribute("attachments", filenames);
		System.out.println("Problem here:"+idea.getStatus());
		if(idea.getStatus().equals("Approved")){
	      		System.out.println("IDEA Approving selected");
	      		System.out.println("ideaid by idea:"+idea.getIdea_id());
				rd = request.getRequestDispatcher("Approved.jsp");
		}else{
			if(idea.getStatus().equals("Pending Approval")){
	      		System.out.println("IDEA selected");
				rd = request.getRequestDispatcher("Pending_Approval.jsp");
		}else{
			if(idea.getStatus().equals("Pending Review By Panel")){
	      		System.out.println("IDEA selected in pending");
				rd = request.getRequestDispatcher("PendingReview.jsp");
		}else{
			if(idea.getStatus().equals("Reviewed By Panel")){
	      		System.out.println("IDEA selected");
				rd = request.getRequestDispatcher("Reviewed.jsp");
		}else{
			if(idea.getStatus().equals("Rejected")){
	      		System.out.println("IDEA selected");
				rd = request.getRequestDispatcher("Rejected.jsp");
		}}}}}
				rd.forward(request, response); 
		}
		catch (SQLException | NullPointerException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}

	}

}
