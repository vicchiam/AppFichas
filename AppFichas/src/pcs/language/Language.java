package pcs.language;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;

public class Language extends Generic<Language> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String path;
	private int state;
	
	public Language(LanguageBuilder builder){
		super(builder.id);
		this.name=builder.name;
		this.path=builder.path;
		this.state=builder.state;
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
	public Language autoMake(ResultSet rs) throws SQLException {
		
		return null;
	}
	
}
