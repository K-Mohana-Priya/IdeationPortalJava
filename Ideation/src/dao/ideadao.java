package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import utility.DatabaseUtility;

import java.util.Date;

import Beans.ideabean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ideadao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet rs;
	public void deleteIdeabyId(int id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("delete from idea where idea_id=?");
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		DatabaseUtility.closeConnection(connection);
	}
	public void deleteIdeabyUId(String id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("delete from idea where user_id=?");
		preparedStatement.setString(1, id);
		preparedStatement.execute();
		DatabaseUtility.closeConnection(connection);
	}
	public ideabean getIdeabyUId(String id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		String sql="select * from idea where idea_id="+Integer.parseInt(id);
		
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		System.out.println("idea id:  "+sql);
		rs = preparedStatement.executeQuery();
		ideabean idea=null;
		if(rs.next()){
			idea=new ideabean(rs.getInt(1),rs.getString(2),
				rs.getString(4),rs.getString(3),rs.getInt(6),rs.getInt(8),rs.getBoolean(9),
				rs.getString(10),rs.getDate(5),rs.getDate(7),rs.getString(11));
		}
		DatabaseUtility.closeConnection(connection);
		return idea;
	}
	public String[] getIdeaAttachmentbyId(int id) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		int c=0;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select count(*) as cnt from idea_attachment where idea_id=?");
		preparedStatement.setInt(1, id);
		rs = preparedStatement.executeQuery();
		if(rs.next()){
			c=rs.getInt("cnt");
		}
		String[] filenames= new String[c];
		preparedStatement=null;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select * from idea_attachment where idea_id=?");
		preparedStatement.setInt(1, id);
		rs = preparedStatement.executeQuery();
		c=0;
		while(rs.next()){
			filenames[c++]=rs.getString(2);
			System.out.println("file name: "+filenames[c-1]);
		}
		DatabaseUtility.closeConnection(connection);
		return filenames;
	}
	
	public String updateIdea(int idea_id,String comment,int rating,String status,String title,String user_id){
		int count=0;
		Date today = new Date();
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		String message=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
		connection = DatabaseUtility.getConnection();
		if(status.equals("reject")){
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("update idea set status='Rejected' where idea_id=?");
		preparedStatement.setInt(1, idea_id);
		preparedStatement.executeUpdate();
		preparedStatement=null;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("update idea set date_approved='"+format.format(today)+"' where idea_id=?");
		preparedStatement.setInt(1, idea_id);
		preparedStatement.executeUpdate();

		message = "Your idea titled "+title+" is rejected.";
	}
	else{
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("update idea set status='Approved' where idea_id=?");
		preparedStatement.setInt(1, idea_id);
		preparedStatement.executeUpdate();
		preparedStatement=null;
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("update idea set date_approved='"+format.format(today)+"' where idea_id=?");
		preparedStatement.setInt(1, idea_id);
		preparedStatement.executeUpdate();
		
			ps2=(PreparedStatement) connection
			.prepareStatement("select no_of_ideas from user where user_id=?");
			ps2.setString(1, user_id);
			rs=ps2.executeQuery();
			if(rs.next()){
				count=Integer.parseInt(rs.getString("no_of_ideas"));
				count++;
			}
			
			ps3= (PreparedStatement) connection
					.prepareStatement("update user set no_of_ideas="+count+" where user_id=?");
			ps3.setString(1, user_id);
			ps3.executeUpdate();
			message = "Your idea titled "+title+" is approved.";
		}
		ps1 = (PreparedStatement) connection
				.prepareStatement("update approval set comments='"+comment+"' where idea_id="+idea_id);
		ps1.executeUpdate();
		
		ps1=null;
		ps1 = (PreparedStatement) connection
				.prepareStatement("update approval set rating="+rating+" where idea_id="+idea_id);
		ps1.executeUpdate();
		
		DatabaseUtility.closeConnection(connection);
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	public void updateReviewIdea(String user_id,int idea_id,String comment,int rating,String title,String idea_user_id){
		int count=0;
		int no_of_panelist=0;
		try{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into review values("+idea_id+",'"+user_id+"',"+rating+",'"+comment+"')");
		preparedStatement.executeUpdate();
		
		Date today = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		
		       ps2=(PreparedStatement) connection
				.prepareStatement("select count(*) from review where idea_id=?");
				ps2.setInt(1, idea_id);
				ResultSet rs = ps2.executeQuery();
				if(rs.next()){
					count=rs.getInt(1);
				}
				System.out.println("Count of reviews : "+count);
				 ps3=(PreparedStatement) connection
							.prepareStatement("select count(*) from user where panelist_flag=true and valid=true");
							rs=ps3.executeQuery();
							while(rs.next()){
								no_of_panelist=rs.getInt(1);
							}
							
				if(count==no_of_panelist){
					ps1 = (PreparedStatement) connection
							.prepareStatement("update idea set status='Reviewed By Panel' where idea_id=?");
					ps1.setInt(1, idea_id);
					ps1.executeUpdate();
					ps1=null;
					System.out.println("Date value : "+format1.format(today));
					ps1 = (PreparedStatement) connection
							.prepareStatement("update idea set date_approved='"+format1.format(today)+"' where idea_id=?");
					ps1.setInt(1, idea_id);
					ps1.executeUpdate();
					notificationsdao nda=new notificationsdao();
					String message = "Your idea titled "+title+" is approved.";
					nda.insertNotification(idea_user_id,message);
				}
			
		DatabaseUtility.closeConnection(connection);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
