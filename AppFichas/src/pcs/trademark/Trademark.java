package pcs.trademark;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.main.Params;

public class Trademark implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int id_image;
	private String path;
	
	public Trademark() {
		super();
		this.id=0;
		this.name="";
		this.id_image=0;
		this.path="";
	}
	
	public Trademark(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.id_image=0;
		this.path="";
	}

	public Trademark(int id, String name, int id_image, String path) {
		super();
		this.id = id;
		this.name = name;
		this.id_image=id_image;
		this.path=path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId_image() {
		return id_image;
	}

	public void setId_image(int id_image) {
		this.id_image = id_image;
	}

	public String getPath() {
		if(path==null || path.length()==0){
			return Params.NO_IMAGE;
		}
		return path;
	}

	public void setPath(String path) {		
		this.path = path;
	}

	public static Trademark makeTrademark(ResultSet rs) throws SQLException{
		int id=rs.getInt("id");
		String name=rs.getString("name");
		int id_image=rs.getInt("id_image");
		String path=rs.getString("path");
		return new Trademark(id,name,id_image,path);
	}
	
	
}
