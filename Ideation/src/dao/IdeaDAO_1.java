package dao;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import utility.DatabaseUtility;

public class IdeaDAO_1 {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet rs;
	
	public int insertIdea(String tit,String idea,String[] files,int file_c,String id,String status,String grp)
			throws SQLException, ClassNotFoundException, NullPointerException{
		
		
		connection = DatabaseUtility.getConnection();
		String sql="";
		sql="select max(idea_id) as mi from idea";
		System.out.println("select idea id:"+sql);
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		rs=preparedStatement.executeQuery();	
		int idea_id=0;
		if(rs.next())
		idea_id=rs.getInt(1);
		
		idea_id++;
		System.out.println("idea id:"+idea_id);
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		sql="insert into idea values ("+idea_id+",'"+id+"','"+tit+"','"+idea+"','"+format.format(today)+"',0,'"+format.format(today)+"',0,false,'"+status+"','"+grp+"')";
		System.out.println("insert idea:"+sql);
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		System.out.println("insert idea:"+sql);
		preparedStatement.executeUpdate();

		System.out.println("insert idea:"+sql);

		for (int i=0;i<file_c;i++) {
			System.out.println("file name:"+files[i]);
			sql="insert into idea_attachment values ("+idea_id+",'"+files[i]+"')";
			System.out.println("insert idea_attachment:"+sql);
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.executeUpdate(); 
		}
	System.out.println("outside file attachment");
		return idea_id;
	}
	public void insertIdeaForApproval(String tit,String idea,String[] files,int file_c,String id,
			String manager_id,String group_name)
			throws SQLException, ClassNotFoundException, NullPointerException {

		connection = DatabaseUtility.getConnection();
				
		int idea_id=insertIdea(tit,idea,files,file_c,id,"Pending Approval",group_name);
		
		String sql="insert into approval values("+idea_id+",'"+manager_id+"',0,0,'')";
		System.out.println("approval idea :"+sql);
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		DatabaseUtility.closeConnection(connection);
		
	}
	
	public void insertIdeaForReview(String tit,String idea,String[] files,int file_c,String id,String message)
			throws SQLException, ClassNotFoundException, NullPointerException {

		int idea_id=insertIdea(tit,idea,files,file_c,id,"Pending Review By Panel","");
        userdao uda = new userdao();
        notificationsdao nda=new notificationsdao();
		String [] panelist=uda.getPanelist();
		int count=uda.getPanelistCount();
		 for(int l=0;l<count;l++){
	    nda.insertNotification(panelist[l], message);
	 }
 	   System.out.println("finished submitting");
		DatabaseUtility.closeConnection(connection);
		
	}
}