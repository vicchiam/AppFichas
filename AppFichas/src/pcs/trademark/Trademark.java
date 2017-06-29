package pcs.trademark;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;
import pcs.utils.Params;

public class Trademark extends Generic<Trademark> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String path;
	private int state;
	
	public Trademark(TrademarkBuilder builder){
		super(builder.id);
		this.name=builder.name;
		this.path=builder.path;
		this.state=builder.state;
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
		return TrademarkBuilder.trademark()
				.withId(id)
				.withName(name)
				.withPath(path)
				.withState(state)
				.build();
	}
	
}
