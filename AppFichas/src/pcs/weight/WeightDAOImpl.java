package pcs.weight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import pcs.abstracts.DAO;
import pcs.interfacesDAO.WeightDAO;
import pcs.utils.JDBCUtil;

public class WeightDAOImpl extends DAO<Weight> implements WeightDAO{
	
	private String SELECT_ID_SQL="SELECT id, value, id_weight_unit FROM weight WHERE id=?";
	private String INSERT_SQL="INSERT INTO weight (value,id_weight_unit) VALUES(?,?)";
	private String UPDATE_SQL="UPDATE weight SET value=?, id_weight_unit=? WHERE id=?";
	private String DELETE_SQL="DELETE FROM weight WHERE id=?";
	
	@Override
	public Weight getWeight(int id) {
		Weight weight=new Weight();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1, id);
			weight=super.get(ps, weight);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public Weight createWeight(Weight weight) {		
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, weight.getValue());	
			ps.setInt(2,weight.getWeightUnit().getId());
			int id=super.insert(ps);
			weight.setId(id);
            return weight;
			
		} catch (SQLException e) {			
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
			int res=super.update(ps);
			if(res>0){
				return weight;
			}
            return weight;
			
		} catch (SQLException e) {			
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
			return super.operation(ps);	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}

}
