package pcs.trademark;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.interfaces.AutoMake;
import pcs.utils.Params;

public class Trademark implements AutoMake<Trademark>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String path;
	private int state;
	
	public Trademark() {
		super();
		this.id=0;
		this.name="";
		this.path="";
		this.state=1;
	}
	
	public Trademark(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.path="";
		this.state=1;
	}

	public Trademark(int id, String name, String path, int state) {
		super();
		this.id = id;
		this.name = name;		
		this.path=path;
		this.state=state;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public Trademark autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		String name=rs.getString("name");
		String path=rs.getString("path");
		int state=rs.getInt("state");
		return new Trademark(id,name,path,state);
	}
	
}
