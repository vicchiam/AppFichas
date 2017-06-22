package pcs.weightUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import pcs.utils.JDBCUtil;

public class WeightUnitDAOImpl implements WeightUnitDAO {
	
	private String SELECT_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE state=? ORDER BY name";
	private String SELECT_ID_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE id=?";
	private String SELECT_NAME_SQL="SELECT id, name, conversionToKgm, state FROM weight_unit WHERE name like ?";
	private String INSERT_SQL="INSERT INTO weight_unit (name,conversionToKgm) VALUE(?,?)";
	private String UPDATE_SQL="UPDATE weight_unit SET name=?, conversionToKgm=? WHERE id=?";
	private String CHANGE_STATE_SQL="UPDATE weight_unit SET state=if(state=0,1,0) WHERE id=?";

	@Override
	public Collection<WeightUnit> listWeightUnits(int state) {
		Collection<WeightUnit> list=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_SQL);	
			ps.setInt(1, state);
			ResultSet rs=ps.executeQuery();			
			while(rs.next()){
				list.add(WeightUnit.makeWeightUnit(rs));
			}			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
			e.printStackTrace();			
		}
		return list;
	}

	@Override
	public WeightUnit getWeightUnit(int id) {
		WeightUnit weightUnit=new WeightUnit();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				weightUnit=WeightUnit.makeWeightUnit(rs);
			}				
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
			e.printStackTrace();
		}	
		return weightUnit;
	}

	@Override
	public WeightUnit getWeightUnit(String name) {
		WeightUnit weightUnit=new WeightUnit();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_NAME_SQL);
			ps.setString(1, name);			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				weightUnit=WeightUnit.makeWeightUnit(rs);
			}				
			rs.close();
			ps.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
			e.printStackTrace();
		}	
		return weightUnit;
	}

	@Override
	public WeightUnit createWeightUnit(WeightUnit weightUnit) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, weightUnit.getName());	
			ps.setFloat(2,weightUnit.getConversionToKgm());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
            	int last_inserted_id = rs.getInt(1);
            	weightUnit.setId(last_inserted_id);
            }
            rs.close();
			ps.close();
            return weightUnit;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
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
			int res=ps.executeUpdate();			
			ps.close();
			if(res>0){
				return weightUnit;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
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
			int res=ps.executeUpdate();			
			ps.close();
			if(res>0){
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
			e.printStackTrace();
		}
		return false;
	}
	
	

}
