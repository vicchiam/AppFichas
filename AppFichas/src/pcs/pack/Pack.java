package pcs.pack;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.Generic;
import pcs.weight.Weight;
import pcs.weight.WeightBusiness;

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

	public Collection<Weight> getWeights() throws SQLException {
		if(this.weights==null){
			this.reloadWeights();
		}
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
	
	public void reloadWeights() throws SQLException{
		this.weights=new WeightBusiness().listWeightsFromPack(super.getId());
	}
	
	public String getWeightNames() throws SQLException{
		this.getWeights();
		
		List<String> names=new ArrayList<>();
		for(Weight weight : this.weights){
			names.add(weight.getValue()+" "+weight.getWeightUnit().getName());
		}
		return String.join(" + ", names);			
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
