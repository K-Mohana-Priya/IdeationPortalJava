package Beans;

public class notificationsbean {
public int notification_id;
public String user_id;
public String notification_data;
public notificationsbean(int notification_id, String user_id,
		String notification_data) {
	super();
	this.notification_id = notification_id;
	this.user_id = user_id;
	this.notification_data = notification_data;
}
public int getNotification_id() {
	return notification_id;
}
public void setNotification_id(int notification_id) {
	this.notification_id = notification_id;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getNotification_data() {
	return notification_data;
}
public void setNotification_data(String notification_data) {
	this.notification_data = notification_data;
}

}
