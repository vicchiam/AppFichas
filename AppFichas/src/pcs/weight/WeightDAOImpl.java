package pcs.weight;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.WeightDAO;

public class WeightDAOImpl extends GenericDAO<Weight> implements WeightDAO{
	
	private String SELECT_ID_SQL=""
			+ "SELECT "
			+ "	w.id, "
			+ "	w.value, "
			+ "	wu.id as id_weight_unit, "
			+ "	wu.name, "
			+ "	wu.conversionToKgm, "
			+ "	wu.state "
			+ "FROM "
			+ "	weight w "
			+ "	LEFT JOIN "
			+ "	weight_unit wu "
			+ "		ON "
			+ "			wu.id=w.id_weight_unit and"
			+ "			wu.state=1 "
			+ "WHERE "
			+ "	w.id=?";
	private String INSERT_SQL="INSERT INTO weight (value,id_weight_unit) VALUES(?,?)";
	private String UPDATE_SQL="UPDATE weight SET value=?, id_weight_unit=? WHERE id=?";
	private String DELETE_SQL="DELETE FROM weight WHERE id=?";
	private String SELECT_PACK_WEIGHT_ID=""
			+ "SELECT"
			+ "	w.id, "
			+ "	w.value, "
			+ "	wu.id as id_weight_unit, "
			+ "	wu.name, "
			+ "	wu.conversionToKgm, "
			+ "	wu.state "
			+ "FROM "
			+ "	pack_weight pw "
			+ "	LEFT JOIN "
			+ "	weight w "
			+ "		ON "
			+ "			w.id=pw.id_weight "
			+ "	LEFT JOIN weight_unit wu "
			+ "		ON "
			+ "			wu.id=w.id_weight_unit and"
			+ "			wu.state=1 "
			+ "WHERE "
			+ "	pw.id_pack=?";
	
	private GenericDAO<Weight> genericDAO;
	
	public WeightDAOImpl(){
		this.genericDAO=new GenericDAO<>();
	}
	
	@Override
	public Weight getWeight(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, WeightBuilder.weight().build());
	}

	@Override
	public Weight insertWeight(Weight weight) throws SQLException {		
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
		params.add(weight.getId());
		return this.genericDAO.update(UPDATE_SQL, params, weight);
	}

	@Override
	public boolean deleteWeight(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(DELETE_SQL, params);
	}
	
	@Override
	public Collection<Weight> listWeightsFromPack(int idPack) throws SQLException{
		List<Object> params=new ArrayList<>();
		params.add(idPack);
		return this.genericDAO.list(SELECT_PACK_WEIGHT_ID, params, WeightBuilder.weight().build());
	}

}
