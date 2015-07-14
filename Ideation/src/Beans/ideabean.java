package Beans;

import java.sql.Date;

public class ideabean {
public int idea_id;
public String user_id;
public String idea_body;
public String title;
public int no_of_views;
public int no_of_votes;
public Boolean draft;
public String status;
public Date date_added;
public Date date_approved;
public String group_name;
public ideabean(int idea_id, String user_id, String idea_body, String title,
		int no_of_views, int no_of_votes, Boolean draft, String status,
		Date date_added, Date date_approved,String group_name) {
	this.idea_id = idea_id;
	this.user_id = user_id;
	this.idea_body = idea_body;
	this.title = title;
	this.no_of_views = no_of_views;
	this.no_of_votes = no_of_votes;
	this.draft = draft;
	this.status = status;
	this.date_added = date_added;
	this.date_approved = date_approved;
	this.group_name = group_name;
}
public int getIdea_id() {
	return idea_id;
}
public void setIdea_id(int idea_id) {
	this.idea_id = idea_id;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getIdea_body() {
	return idea_body;
}
public void setIdea_body(String idea_body) {
	this.idea_body = idea_body;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getNo_of_views() {
	return no_of_views;
}
public void setNo_of_views(int no_of_views) {
	this.no_of_views = no_of_views;
}
public int getNo_of_votes() {
	return no_of_votes;
}
public void setNo_of_votes(int no_of_votes) {
	this.no_of_votes = no_of_votes;
}
public Boolean getDraft() {
	return draft;
}
public void setDraft(Boolean draft) {
	this.draft = draft;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getDate_added() {
	return date_added;
}
public void setDate_added(Date date_added) {
	this.date_added = date_added;
}
public Date getDate_approved() {
	return date_approved;
}
public void setDate_approved(Date date_approved) {
	this.date_approved = date_approved;
}
public String getGroup_name() {
	return group_name;
}
public void setGroup_name(String group_name) {
	this.group_name = group_name;
}
}

