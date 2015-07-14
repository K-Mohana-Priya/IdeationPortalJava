package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.messagedao;

/**
 * Servlet implementation class DeleteMsg
 */
public class DeleteMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMsg() {
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
			HttpSession ses=request.getSession();
        int role=(int)ses.getAttribute("roleid");
		messagedao msgd = new messagedao();
		System.out.println("Delete item: "+request.getParameter("ticked"));
		msgd.deleteMessagebyUId(request.getParameter("ticked"));
		RequestDispatcher rd;
		if(role==3)
		rd = request.getRequestDispatcher("user_profile.jsp");
		else
	    rd = request.getRequestDispatcher("adminmessage.jsp");	
		rd.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
