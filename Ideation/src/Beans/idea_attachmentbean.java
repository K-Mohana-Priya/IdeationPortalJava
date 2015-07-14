package Beans;

public class idea_attachmentbean {
public int idea_id;
public String attachment;
public idea_attachmentbean(int idea_id, String attachment) {
	this.idea_id = idea_id;
	this.attachment = attachment;
}
public int getIdea_id() {
	return idea_id;
}
public void setIdea_id(int idea_id) {
	this.idea_id = idea_id;
}
public String getAttachment() {
	return attachment;
}
public void setAttachment(String attachment) {
	this.attachment = attachment;
}

}
