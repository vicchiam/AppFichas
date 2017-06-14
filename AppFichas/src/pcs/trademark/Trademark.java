package pcs.trademark;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Trademark implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public Trademark() {
		super();
		this.id=0;
		this.name="";
	}

	public Trademark(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	public static Trademark makeTrademark(ResultSet rs) throws SQLException{
		int id=rs.getInt("id");
		String name=rs.getString("name");
		return new Trademark(id,name);
	}
	
	
}
