package pcs.longitudeUnit;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;

public class LongitudeUnit extends Generic<LongitudeUnit> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private float conversionToMeters;
	private int state;
	
	public LongitudeUnit(LongitudeUnitBuilder builder){
		super(builder.id);
		this.name=builder.name;
		this.conversionToMeters=builder.conversionToMeters;
		this.state=builder.state;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getConversionToMeters() {
		return conversionToMeters;
	}

	public void setConversionToMeters(float conversionToMeters) {
		this.conversionToMeters = conversionToMeters;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public LongitudeUnit autoMake(ResultSet rs) throws SQLException {
		
		return null;
	}

}
