package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.userbean;
import dao.ideadao;
import dao.messagedao;

/**
 * Servlet implementation class SendMes
 */
public class SendMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMes() {
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
		String user_id = request.getParameter("user_id");
		String mail_id = request.getParameter("mail");
		String data = request.getParameter("data");
		messagedao ida=new messagedao();
		ida.insertMessage(user_id,mail_id,data);
		RequestDispatcher rd=null;
		rd = request.getRequestDispatcher("user_profile.jsp");
		rd.forward(request, response); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
