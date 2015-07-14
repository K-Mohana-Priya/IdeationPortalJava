package Beans;

import java.sql.Date;

public class userbean {
public String user_id;
public String user_name;
public String email;
public String password;
public String mobile_no;
public String manager_id_1;
public String manager_id_2;
public Boolean panelist_flag;
public Boolean valid;
public Date date_joined;
public int level;
public int layer;
public int no_of_ideas;
public userbean(String user_id, String user_name, String email,
		String password,Date date_joined, int level, int layer, int no_of_ideas,
		String mobile_no, String manager_id_1,
		String manager_id_2, Boolean panelist_flag, Boolean valid) {
	this.user_id = user_id;
	this.user_name = user_name;
	this.email = email;
	this.password = password;
	this.mobile_no = mobile_no;
	this.manager_id_1 = manager_id_1;
	this.manager_id_2 = manager_id_2;
	this.panelist_flag = panelist_flag;
	this.valid = valid;
	this.date_joined = date_joined;
	this.level = level;
	this.layer = layer;
	this.no_of_ideas = no_of_ideas;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getMobile_no() {
	return mobile_no;
}
public void setMobile_no(String mobile_no) {
	this.mobile_no = mobile_no;
}
public String getManager_id_1() {
	return manager_id_1;
}
public void setManager_id_1(String manager_id_1) {
	this.manager_id_1 = manager_id_1;
}
public String getManager_id_2() {
	return manager_id_2;
}
public void setManager_id_2(String manager_id_2) {
	this.manager_id_2 = manager_id_2;
}
public Boolean getPanelist_flag() {
	return panelist_flag;
}
public void setPanelist_flag(Boolean panelist_flag) {
	this.panelist_flag = panelist_flag;
}
public Boolean getValid() {
	return valid;
}
public void setValid(Boolean valid) {
	this.valid = valid;
}
public Date getDate_joined() {
	return date_joined;
}
public void setDate_joined(Date date_joined) {
	this.date_joined = date_joined;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getLayer() {
	return layer;
}
public void setLayer(int layer) {
	this.layer = layer;
}
public int getNo_of_ideas() {
	return no_of_ideas;
}
public void setNo_of_ideas(int no_of_ideas) {
	this.no_of_ideas = no_of_ideas;
}

}
