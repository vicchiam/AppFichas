package pcs.pack;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;

public class Pack extends Generic<Pack> implements Serializable{

	private static final long serialVersionUID = 1L;

	private String description;
	private String mesure;
	private String weight;
	private int apt;
	private int state;
	
	public Pack() {
		super(0);
		this.description = "";
		this.mesure="";
		this.weight = "";
		this.apt = 0;
		this.state = 0;
	}
	
	public Pack(int id, String description, String mesure, String weight, int apt, int state) {
		super(id);
		this.description = description;
		this.mesure=mesure;
		this.weight = weight;
		this.apt = apt;
		this.state=state;
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMesure() {
		return mesure;
	}

	public void setMesure(String mesure) {
		this.mesure = mesure;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getApt() {
		return apt;
	}

	public void setApt(int apt) {
		this.apt = apt;
	}			
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public Pack autoMake(ResultSet rs) throws SQLException {
		
		int id=rs.getInt("id");
		String description=rs.getString("description");
		String mesure=rs.getString("mesure");
		String weight=rs.getString("weight");
		int apt=rs.getInt("apt");
		int state=rs.getInt("state");				
		
		return new Pack(id, description, mesure, weight, apt, state);
	}
	
	
}
