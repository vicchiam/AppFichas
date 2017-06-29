package pcs.weight;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;
import pcs.interfacesDAO.WeightUnitDAO;
import pcs.utils.Params;
import pcs.weightUnit.WeightUnit;
import pcs.weightUnit.WeightUnitBuilder;
import pcs.weightUnit.WeightUnitBusiness;
import pcs.weightUnit.WeightUnitDAOImpl;

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
		if(this.getWeightUnit().getId()!=Params.EMPTY_ID && this.weightUnit.getName().equals("")){
			this.weightUnit=loadWeightUnit();
		}
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
		
		WeightUnit weightUnit=WeightUnitBuilder.weightUnit()
				.withId(idWeightUnit)
				.build();
		
		return WeightBuilder.weight()
				.withId(id)
				.withValue(value)
				.withWeightUnit(weightUnit)
				.build();		
	}	
	
	private WeightUnit loadWeightUnit() throws SQLException{
		WeightUnitBusiness weightUnitBusiness=new WeightUnitBusiness();
		return weightUnitBusiness.getWeightUnit(this.weightUnit.getId());
	}
	
}
