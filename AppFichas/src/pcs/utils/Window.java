package pcs.utils;

import java.io.Serializable;

public class Window implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private int width;
	private int height;
	private String title;
		
	public Window(String id, int width, int height, String title) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.title = title;
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

}
