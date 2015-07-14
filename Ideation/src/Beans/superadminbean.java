package Beans;

public class superadminbean {
	public int role_id;
	public int screen_id;
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}
	public superadminbean(int role_id, int screen_id) {
		this.role_id = role_id;
		this.screen_id = screen_id;
	}
}
