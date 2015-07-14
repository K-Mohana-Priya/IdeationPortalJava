package Beans;

public class screensbean {
public int screen_id;
public String screen_name;
public String file_name;
public screensbean(int screen_id, String screen_name, String file_name) {
	this.screen_id = screen_id;
	this.screen_name = screen_name;
	this.file_name = file_name;
}
public int getScreen_id() {
	return screen_id;
}
public String getFile_name() {
	return file_name;
}
public void setFile_name(String file_name) {
	this.file_name = file_name;
}
public void setScreen_id(int screen_id) {
	this.screen_id = screen_id;
}
public String getScreen_name() {
	return screen_name;
}
public void setScreen_name(String screen_name) {
	this.screen_name = screen_name;
}

}
