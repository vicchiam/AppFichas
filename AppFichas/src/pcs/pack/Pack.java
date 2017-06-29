package pcs.pack;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import pcs.generic.Generic;
import pcs.weight.Weight;

public class Pack extends Generic<Pack> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public final static String[] aptNames={"No Apto", "Apto"};	
	public final static int APT_EMPTY=0;
	public final static int NO_APT=1;
	public final static int APT=2;	

	private String description;
	private Collection<Weight> weights;
	private int apt;
	private int state;
	private String mesure;
	
	
	public Pack(PackBuilder builder){
		super(builder.id);
		this.description=builder.description;
		this.weights=builder.weights;
		this.apt=builder.apt;
		this.state=builder.state;
		this.mesure=builder.mesure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	public Collection<Weight> getWeights() {
		return weights;
	}

	public void setWeights(Collection<Weight> weights) {
		this.weights = weights;
	}

	public String getMesure() {
		return mesure;
	}

	public void setMesure(String mesure) {
		this.mesure = mesure;
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
	
	public String getAptName(){
		return Pack.aptNames[this.apt-1];
	}

	@Override
	public Pack autoMake(ResultSet rs) throws SQLException {
		
		int id=rs.getInt("id");
		String description=rs.getString("description");
		String mesure=rs.getString("mesure");
		int apt=rs.getInt("apt");
		int state=rs.getInt("state");				
		
		return PackBuilder.pack()
				.withId(id)
				.withDescription(description)
				.withApt(apt)
				.withState(state)
				.withMesure(mesure)
				.build();
				
	}
	
	
}
