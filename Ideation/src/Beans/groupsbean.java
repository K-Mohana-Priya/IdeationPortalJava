package Beans;

public class groupsbean {
public int group_id;
public String group_name;
public int no_of_users;
public groupsbean(int group_id, String group_name, int no_of_users) {
	this.group_id = group_id;
	this.group_name = group_name;
	this.no_of_users = no_of_users;
}
public int getGroup_id() {
	return group_id;
}
public void setGroup_id(int group_id) {
	this.group_id = group_id;
}
public String getGroup_name() {
	return group_name;
}
public void setGroup_name(String group_name) {
	this.group_name = group_name;
}
public int getNo_of_users() {
	return no_of_users;
}
public void setNo_of_users(int no_of_users) {
	this.no_of_users = no_of_users;
}

}
