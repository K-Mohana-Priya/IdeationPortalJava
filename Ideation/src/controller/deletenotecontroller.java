package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.messagedao;
import dao.notificationsdao;

/**
 * Servlet implementation class deletenotecontroller
 */
@WebServlet("/deletenotecontroller")
public class deletenotecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletenotecontroller() {
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
			notificationsdao notd = new notificationsdao();
			System.out.println("Delete item: "+request.getParameter("ticked"));
			notd.deleteNotificationbyId(Integer.parseInt(request.getParameter("ticked")));
			RequestDispatcher rd = request.getRequestDispatcher("adminnotification.jsp");
			rd.forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

}
