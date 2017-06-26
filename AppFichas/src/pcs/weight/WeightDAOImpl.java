package pcs.weight;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.WeightDAO;

public class WeightDAOImpl extends GenericDAO<Weight> implements WeightDAO{
	
	private String SELECT_ID_SQL="SELECT id, value, id_weight_unit FROM weight WHERE id=?";
	private String INSERT_SQL="INSERT INTO weight (value,id_weight_unit) VALUES(?,?)";
	private String UPDATE_SQL="UPDATE weight SET value=?, id_weight_unit=? WHERE id=?";
	private String DELETE_SQL="DELETE FROM weight WHERE id=?";
	
	private GenericDAO<Weight> genericDAO;
	
	public WeightDAOImpl(){
		this.genericDAO=new GenericDAO<>();
	}
	
	@Override
	public Weight getWeight(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, new Weight());
	}

	@Override
	public Weight createWeight(Weight weight) throws SQLException {		
		List<Object> params=new ArrayList<>();
		params.add(weight.getValue());
		params.add(weight.getWeightUnit().getId());
		return this.genericDAO.insert(INSERT_SQL, params, weight);		
	}

	@Override
	public Weight updateWeight(Weight weight) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(weight.getValue());
		params.add(weight.getWeightUnit().getId());
		return this.genericDAO.update(UPDATE_SQL, params, weight);
	}

	@Override
	public boolean deleteWeight(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(DELETE_SQL, params);
	}

}
