package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ideadao;

/**
 * Servlet implementation class DeleteMsg
 */
public class DeleteIdea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteIdea() {
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
		ideadao idead = new ideadao();
		System.out.println("Delete item: "+request.getParameter("ticked"));
		idead.deleteIdeabyId(Integer.parseInt(request.getParameter("ticked")));
		RequestDispatcher rd = request.getRequestDispatcher("user_profile.jsp");
		rd.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
