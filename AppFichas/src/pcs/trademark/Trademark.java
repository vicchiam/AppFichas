package pcs.trademark;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.utils.AutoMake;
import pcs.utils.Params;

public class Trademark implements AutoMake<Trademark>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String path;
	
	public Trademark() {
		super();
		this.id=0;
		this.name="";
		this.path="";
	}
	
	public Trademark(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.path="";
	}

	public Trademark(int id, String name, String path) {
		super();
		this.id = id;
		this.name = name;		
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
		String path=rs.getString("path");
		return new Trademark(id,name,path);
	}

	@Override
	public Trademark autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		String name=rs.getString("name");
		String path=rs.getString("path");
		return new Trademark(id,name,path);
	}
	
	
}
