package model;

public class Resource {
	private String item;
	private String locked_icon;
	private String unlocked_icon;
	
	private boolean unlocked;
	
	public Resource(String item) {
		this.item = item;
		locked_icon = "images/default_lock.gif";
		unlocked_icon = "images/default_unlocked.gif";
		unlocked = false;
	}

	@Override
	public String toString() {
		return "Resource [item=" + item + "]";
	}
}
