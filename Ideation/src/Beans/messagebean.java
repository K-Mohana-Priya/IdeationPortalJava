package Beans;

public class messagebean {
public int message_id;
public String message_data;
public String user_id;
public String sending_user_id;
public messagebean(int message_id, String message_data, String user_id,
		String sending_user_id) {
	this.message_id = message_id;
	this.message_data = message_data;
	this.user_id = user_id;
	this.sending_user_id = sending_user_id;
}
public int getMessage_id() {
	return message_id;
}
public void setMessage_id(int message_id) {
	this.message_id = message_id;
}
public String getMessage_data() {
	return message_data;
}
public void setMessage_data(String message_data) {
	this.message_data = message_data;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getSending_user_id() {
	return sending_user_id;
}
public void setSending_user_id(String sending_user_id) {
	this.sending_user_id = sending_user_id;
}

}
