package pcs.weightUnit;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.utils.AutoMake;

public class WeightUnit implements AutoMake<WeightUnit>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private float conversionToKgm;
	private int state;
	
	public WeightUnit(){
		this.id=0;
		this.name="";
		this.conversionToKgm=0;
		this.state=0;
	}
	
	public WeightUnit(int id, String name, float conversionToKgm){
		this.id=id;
		this.name=name;
		this.conversionToKgm=conversionToKgm;
		this.state=1;
	}
	
	public WeightUnit(int id, String name, float conversionToKgm, int state){
		this.id=id;
		this.name=name;
		this.conversionToKgm=conversionToKgm;
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

	public float getConversionToKgm() {
		return conversionToKgm;
	}

	public void setConversionToKgm(float conversionToKgm) {
		this.conversionToKgm = conversionToKgm;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static WeightUnit makeWeightUnit(ResultSet rs) throws SQLException{
		int id=rs.getInt("id");
		String name=rs.getString("name");
		float conversionToKgm=rs.getFloat("conversionToKgm");
		int state=rs.getInt("state");
		return new WeightUnit(id,name,conversionToKgm,state);
	}

	@Override
	public WeightUnit autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		String name=rs.getString("name");
		float conversionToKgm=rs.getFloat("conversionToKgm");
		int state=rs.getInt("state");
		return new WeightUnit(id,name,conversionToKgm,state);
	}

}
