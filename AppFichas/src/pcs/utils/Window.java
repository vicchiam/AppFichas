package pcs.utils;

import java.io.Serializable;

public class Window implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static int CLOSE_BUTTON=0;
	public static int NO_CLOSE_BUTTON=1;
	public static int DEFAULT_LEVEL=1;
	
	private String id;
	private int width;
	private int height;
	private String title;
	private int level;
	private int viewCloseButton;
		
	public Window(String id, int width, int height, String title) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.title = title;
		this.level=Window.DEFAULT_LEVEL;
		this.viewCloseButton=Window.CLOSE_BUTTON;
	}
	
	public Window(String id, int width, int height, String title, int level) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.title = title;
		this.level=level;
		this.viewCloseButton=Window.CLOSE_BUTTON;
	}
	
	public Window(String id, int width, int height, String title, int level, int closeButton) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.title = title;
		this.level=level;
		this.viewCloseButton=closeButton;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getViewCloseButton() {
		return viewCloseButton;
	}

	public void setViewCloseButton(int view_close_button) {
		this.viewCloseButton = view_close_button;
	}		
	
	

}
