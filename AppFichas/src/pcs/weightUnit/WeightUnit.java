package pcs.weightUnit;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;

public class WeightUnit extends Generic<WeightUnit> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private float conversionToKgm;
	private int state;
	
	public WeightUnit(WeightUnitBuilder builder){
		super(builder.id);
		this.name=builder.name;
		this.conversionToKgm=builder.conversionToKgm;
		this.state=builder.state;
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

	@Override
	public WeightUnit autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		String name=rs.getString("name");
		float conversionToKgm=rs.getFloat("conversionToKgm");
		int state=rs.getInt("state");
		return WeightUnitBuilder.weightUnit()
				.withId(id)
				.withName(name)
				.withConversionToKgm(conversionToKgm)
				.withState(state)
				.build();
	}

}
