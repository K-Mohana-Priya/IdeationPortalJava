package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DatabaseUtility;
import Beans.userbean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class userdao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	public userbean getUserbyEmail(String email) throws SQLException
	{
		connection = DatabaseUtility.getConnection();
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("select * from user where email=? and valid=true");
		preparedStatement.setString(1, email);
		resultSet = preparedStatement.executeQuery();
		userbean user = resultSet.next() ? new userbean(resultSet.getString(1),
				resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),
				resultSet.getDate(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),
				resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),
				resultSet.getBoolean(12),resultSet.getBoolean(13)) : null;
		DatabaseUtility.closeConnection(connection);
		return user;
	}
public userbean getUserbyId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select * from user where user_id=? and valid=true");
	preparedStatement.setString(1, id);
	resultSet = preparedStatement.executeQuery();
	userbean user = resultSet.next() ? new userbean(resultSet.getString(1),
			resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),
			resultSet.getDate(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8),
			resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),
			resultSet.getBoolean(12),resultSet.getBoolean(13)) : null;
	DatabaseUtility.closeConnection(connection);
	return user;
}
public void setUser(String user_id, String user_name, String email,
		String password,Date date_joined, int level, int layer, int no_of_ideas,
		String mobile_no, String manager_id_1,
		String manager_id_2, Boolean panelist_flag, Boolean valid) throws SQLException
		{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(2, user_name);
	preparedStatement.setString(3, email);
	preparedStatement.setString(4, password);
	preparedStatement.setDate(5, date_joined);
	preparedStatement.setInt(6,level );
	preparedStatement.setInt(7,layer );
	preparedStatement.setInt(8,no_of_ideas );
	preparedStatement.setString(9, mobile_no);
	preparedStatement.setString(10, manager_id_1);
	preparedStatement.setString(11, manager_id_2);
	preparedStatement.setBoolean(12,panelist_flag);
	preparedStatement.setBoolean(13,valid );
	preparedStatement.execute();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("INSERT INTO jforum_users ( username, user_password,user_email, user_regdate ) VALUES (?,?,?,NOW())");
	preparedStatement.setString(1, user_id);
	preparedStatement.setString(3, email);
	preparedStatement.setString(2, password);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
		}
public void updateUser(String user_id, String user_name, String email,
		Date date_joined, int level, int layer,
		String mobile_no, String manager_id_1,
		String manager_id_2) throws SQLException
		{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("update user set user_name=?,email=?,date_joined=?,level=?,layer=?,mobile_no=?,manager_id_1=?,manager_id_2=? where user_id=?");
	preparedStatement.setString(9, user_id);
	preparedStatement.setString(1, user_name);
	preparedStatement.setString(2, email);
	preparedStatement.setDate(3, date_joined);
	preparedStatement.setInt(4,level );
	preparedStatement.setInt(5,layer );
	preparedStatement.setString(6, mobile_no);
	preparedStatement.setString(7, manager_id_1);
	preparedStatement.setString(8, manager_id_2);
	preparedStatement.execute();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("update jforum_users set user_email=? where username=?");
	preparedStatement.setString(2, user_id);
	preparedStatement.setString(1, email);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
		}
public void updateAdmin(String user_id, String user_name, String email,
		Date date_joined, int level, int layer,
		String mobile_no) throws SQLException
		{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("update user set user_name=?,email=?,date_joined=?,level=?,layer=?,mobile_no=? where user_id=?");
	preparedStatement.setString(7, user_id);
	preparedStatement.setString(1, user_name);
	preparedStatement.setString(2, email);
	preparedStatement.setDate(3, date_joined);
	preparedStatement.setInt(4,level );
	preparedStatement.setInt(5,layer );
	preparedStatement.setString(6, mobile_no);
	preparedStatement.execute();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("update jforum_users set user_email=? where username=?");
	preparedStatement.setString(2, user_id);
	preparedStatement.setString(1, email);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
		}
public void removeUserbyId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("update user set valid=false where user_id=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select user_id from jforum_users where username=?");
	preparedStatement.setString(1, id);
	resultSet = preparedStatement.executeQuery();
	int fuid=0;
	if(resultSet.next())
	{
		fuid=resultSet.getInt(1);
		preparedStatement = (PreparedStatement) connection
				.prepareStatement("delete from jforum_user_groups where user_id=?");
		preparedStatement.setInt(1, fuid);
		preparedStatement.execute();
	}
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from jforum_users where username=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}
public void removeAdminbyId(String id) throws SQLException
{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from user where user_id=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();

	preparedStatement = (PreparedStatement) connection
			.prepareStatement("delete from jforum_users where username=?");
	preparedStatement.setString(1, id);
	preparedStatement.execute();
	DatabaseUtility.closeConnection(connection);
}

public String[] getPanelist(){
	String [] panelist=new String[100];
	try{
	connection = DatabaseUtility.getConnection();
    int count=0;
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select user_id from user where panelist_flag=true and valid=true");
	resultSet = preparedStatement.executeQuery();
	while(resultSet.next()){
		panelist[count++]=resultSet.getString("user_id");
	}
	panelist[count]="";
	}catch(Exception e){
		e.printStackTrace();
	}
	return panelist;
}
public int getPanelistCount(){
    int count=0;
	try{
	connection = DatabaseUtility.getConnection();
	preparedStatement = (PreparedStatement) connection
			.prepareStatement("select count(*) as cnt from user where panelist_flag=true and valid=true");
	resultSet = preparedStatement.executeQuery();
	if(resultSet.next()){
		count=resultSet.getInt("cnt");
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return count;
}
}
