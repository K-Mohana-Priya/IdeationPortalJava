package Beans;

public class approvalbean {
public int idea_id;
public String approving_user_id;
public int rating;
public int level;
public String comments;
public approvalbean(int idea_id, String approving_user_id, int rating,
		int level, String comments) {
	this.idea_id = idea_id;
	this.approving_user_id = approving_user_id;
	this.rating = rating;
	this.level = level;
	this.comments = comments;
}
public int getIdea_id() {
	return idea_id;
}
public void setIdea_id(int idea_id) {
	this.idea_id = idea_id;
}
public String getApproving_user_id() {
	return approving_user_id;
}
public void setApproving_user_id(String approving_user_id) {
	this.approving_user_id = approving_user_id;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}

}
