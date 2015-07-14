package Beans;

public class user_rolebean {
public String user_id;
public int role_id;
public user_rolebean(String user_id, int role_id) {
	this.user_id = user_id;
	this.role_id = role_id;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public int getRole_id() {
	return role_id;
}
public void setRole_id(int role_id) {
	this.role_id = role_id;
}

}
