package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.messagedao;
import dao.notificationsdao;
import Beans.userbean;

/**
 * Servlet implementation class Message_Notify
 */
public class Message_Notify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message_Notify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		HttpSession session=request.getSession();
		response.setContentType("text/xml");
		PrintWriter out=response.getWriter();
		userbean user=(userbean)session.getAttribute("user");
		String id=user.getUser_id();
		String ncount=new Integer(new notificationsdao().getNotificationCount(id)).toString();
		String mcount=new Integer(new messagedao().getMessageCount(id)).toString();
		String xml="<count><message>"+mcount+"</message><notify>"+ncount+"</notify></count>";
		System.out.println("xml output: "+xml);
		out.write(xml);
		out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
