package pcs.weight;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;
import pcs.weightUnit.WeightUnit;
import pcs.weightUnit.WeightUnitBuilder;

public class Weight extends Generic<Weight> implements Serializable{

	private static final long serialVersionUID = 1L;

	private float value;
	private WeightUnit weightUnit;
	
	public Weight(WeightBuilder builder){
		super(builder.id);
		this.value=builder.value;
		this.weightUnit=builder.weightUnit;
	}	

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public WeightUnit getWeightUnit() throws SQLException {
		return this.weightUnit;
	}

	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}
	
	@Override
	public Weight autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		float value=rs.getFloat("value");
		
		int idWeightUnit=rs.getInt("id_weight_unit");
		String name=rs.getString("name");
		float conversion=rs.getFloat("conversionToKgm");
		int state=rs.getInt("state");		
		
		WeightUnit weightUnit=WeightUnitBuilder.weightUnit()
				.withId(idWeightUnit)
				.withName(name)
				.withConversionToKgm(conversion)
				.withState(state)
				.build();
		
		return WeightBuilder.weight()
				.withId(id)
				.withValue(value)
				.withWeightUnit(weightUnit)
				.build();		
	}	
	
}
