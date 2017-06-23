package pcs.weightUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import pcs.abstracts.DAO;
import pcs.interfacesDAO.WeightUnitDAO;
import pcs.utils.JDBCUtil;

public class WeightUnitDAOImpl extends DAO<WeightUnit> implements WeightUnitDAO {
	
	private String SELECT_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE state=? ORDER BY name";
	private String SELECT_ID_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE id=?";
	private String SELECT_NAME_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE name like ?";
	private String INSERT_SQL="INSERT INTO weight_unit (name,conversionToKgm) VALUE(?,?)";
	private String UPDATE_SQL="UPDATE weight_unit SET name=?, conversionToKgm=? WHERE id=?";
	private String CHANGE_STATE_SQL="UPDATE weight_unit SET state=if(state=0,1,0) WHERE id=?";

	@Override
	public Collection<WeightUnit> listWeightUnits(int state) {
		Collection<WeightUnit> listWeightUnits=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_SQL);	
			ps.setInt(1, state);
			listWeightUnits=super.list(ps, new WeightUnit());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listWeightUnits;
	}

	@Override
	public WeightUnit getWeightUnit(int id) {
		WeightUnit weightUnit=new WeightUnit();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1, id);
			weightUnit=super.get(ps, weightUnit);
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public WeightUnit getWeightUnit(String name) {
		WeightUnit weightUnit=new WeightUnit();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_NAME_SQL);
			ps.setString(1, name);			
			weightUnit=super.get(ps, weightUnit);		
		} catch (SQLException e) {			
			e.printStackTrace();
		}			
		return null;
	}

	@Override
	public WeightUnit createWeightUnit(WeightUnit weightUnit) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, weightUnit.getName());	
			ps.setFloat(2,weightUnit.getConversionToKgm());
			ps.executeUpdate();
			int id=super.insert(ps);
			weightUnit.setId(id);
            return weightUnit;			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public WeightUnit updateWeightUnit(WeightUnit weightUnit) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_SQL);
			ps.setString(1, weightUnit.getName());
			ps.setFloat(2, weightUnit.getConversionToKgm());
			ps.setInt(3, weightUnit.getId());
			int res=super.update(ps);	
			if(res>0){
				return weightUnit;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public boolean changeStateWeightUnit(int id) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(CHANGE_STATE_SQL);
			ps.setInt(1, id);
			return super.operation(ps);		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	

}
