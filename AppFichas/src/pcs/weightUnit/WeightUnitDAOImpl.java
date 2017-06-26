package pcs.weightUnit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.WeightUnitDAO;

public class WeightUnitDAOImpl extends GenericDAO<WeightUnit> implements WeightUnitDAO {
	
	private String SELECT_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE state=? ORDER BY name";
	private String SELECT_ID_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE id=?";
	private String SELECT_NAME_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE name like ?";
	private String INSERT_SQL="INSERT INTO weight_unit (name,conversionToKgm) VALUE(?,?)";
	private String UPDATE_SQL="UPDATE weight_unit SET name=?, conversionToKgm=? WHERE id=?";
	private String CHANGE_STATE_SQL="UPDATE weight_unit SET state=if(state=0,1,0) WHERE id=?";

	private GenericDAO<WeightUnit> genericDAO;
	
	public WeightUnitDAOImpl() {
		this.genericDAO=new GenericDAO<>();
	}
	
	@Override
	public Collection<WeightUnit> listWeightUnits(int state) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(state);
		return this.genericDAO.list(SELECT_SQL, params, new WeightUnit());
	}

	@Override
	public WeightUnit getWeightUnit(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, new WeightUnit());
	}

	@Override
	public WeightUnit getWeightUnit(String name) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(name);
		return this.genericDAO.get(SELECT_NAME_SQL, params, new WeightUnit());
	}

	@Override
	public WeightUnit createWeightUnit(WeightUnit weightUnit) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(weightUnit.getName());
		params.add(weightUnit.getConversionToKgm());
		return this.insert(INSERT_SQL, params, weightUnit);
	}

	@Override
	public WeightUnit updateWeightUnit(WeightUnit weightUnit) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(weightUnit.getName());
		params.add(weightUnit.getConversionToKgm());
		params.add(weightUnit.getId());
		return this.genericDAO.update(UPDATE_SQL, params, weightUnit);
	}

	@Override
	public boolean changeStateWeightUnit(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.operation(CHANGE_STATE_SQL, params);
	}
	
	

}
