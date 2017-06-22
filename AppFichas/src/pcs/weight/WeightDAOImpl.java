package pcs.weight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pcs.utils.JDBCUtil;

public class WeightDAOImpl implements WeightDAO{
	
	private String SELECT_ID_SQL="SELECT id, value, id_weight_unit FROM weight WHERE id=?";
	private String INSERT_SQL="INSERT INTO weight (value,id_weight_unit) VALUES(?,?)";
	private String UPDATE_SQL="UPDATE weight SET value=?, id_weight_unit=? WHERE id=?";
	private String DELETE_SQL="DELETE FROM weight WHERE id=?";
	
	@Override
	public Weight getWeight(int id) {
		Weight weight=null;
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				weight=Weight.makeWeight(rs);
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
		return weight;
	}

	@Override
	public Weight createWeight(Weight weight) {		
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, weight.getValue());	
			ps.setInt(2,weight.getWeightUnit().getId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
            	int last_inserted_id = rs.getInt(1);
            	weight.setId(last_inserted_id);
            }
            rs.close();
			ps.close();
            return weight;
			
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
	public Weight updateWeight(Weight weight) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_SQL);
			ps.setFloat(1, weight.getValue());	
			ps.setInt(2,weight.getWeightUnit().getId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
            	int last_inserted_id = rs.getInt(1);
            	weight.setId(last_inserted_id);
            }
            rs.close();
			ps.close();
            return weight;
			
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
	public boolean deleteWeight(int id) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(DELETE_SQL);
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
