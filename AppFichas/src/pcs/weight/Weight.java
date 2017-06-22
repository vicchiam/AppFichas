package pcs.weight;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.utils.AutoMake;
import pcs.weightUnit.WeightUnit;
import pcs.weightUnit.WeightUnitDAO;
import pcs.weightUnit.WeightUnitDAOImpl;

public class Weight implements AutoMake<Weight>, Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private float value;
	private WeightUnit weightUnit;
	
	public Weight() {
		this.id = 0;
		this.value = 0.0f;
		this.weightUnit = new WeightUnit();
	}
	
	public Weight(int id, float value, WeightUnit weightUnit) {
		this.id = id;
		this.value = value;
		this.weightUnit = weightUnit;
	}
	
	public Weight(int id, float value, int idWeightUnit) {
		this.id = id;
		this.value = value;		
		WeightUnitDAO weightUnitDAO=new WeightUnitDAOImpl();		
		this.weightUnit = weightUnitDAO.getWeightUnit(idWeightUnit);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public WeightUnit getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}
	
	public static Weight makeWeight(ResultSet rs) throws SQLException{
		int id=rs.getInt("id");
		float value=rs.getFloat("value");
		int idWeightUnit=rs.getInt("id_weight_unit");		
		
		return new Weight(id,value,idWeightUnit);
	}

	@Override
	public Weight autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		float value=rs.getFloat("value");
		int idWeightUnit=rs.getInt("id_weight_unit");		
		
		return new Weight(id,value,idWeightUnit);
	}
	
	
}
