package pcs.trademark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.users.User;
import pcs.utils.JDBCUtil;

public class TrademarkDAOImpl implements TrademarkDAO{
	
	private String SELECT_SQL="SELECT id, name, id_image FROM trademark ";
	private String SELECT_ID_SQL="SELECT id, name, id_image FROM trademark WHERE id=?";
	private String INSERT_SQL="INSERT INTO trademark (name,id_image) VALUES(?,NULL)";
	private String UPDATE_SQL="UPDATE trademark SET name=? WHERE id=?";
	private String DELETE_SQL="DELETE FROM trademark WHERE id=?";
	
	public TrademarkDAOImpl() {}

	@Override
	public Collection<Trademark> listTrademarks(String name) {
		Collection<Trademark> listTrademarks=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=this.prepareParams(conn, name);				
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				listTrademarks.add(Trademark.makeTrademark(rs));
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
		return listTrademarks;
	}

	@Override
	public Trademark getTrademark(String id) {
		Trademark trademark=new Trademark();
		try {
			Connection conn=JDBCUtil.getConnection();
			List<Object> params=new ArrayList<>();
			params.add(id);				
			PreparedStatement ps=JDBCUtil.getPreparedStatement(conn, SELECT_ID_SQL , params);				
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				trademark=Trademark.makeTrademark(rs);
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
		return trademark;
	}

	@Override
	public Trademark createTrademark(Trademark trademark) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, trademark.getName());					
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
            	int last_inserted_id = rs.getInt(1);
            	trademark.setId(last_inserted_id);
            }
            rs.close();
			ps.close();
            return trademark;
			
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
	public Trademark updateTrademark(Trademark trademark) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_SQL);
			ps.setString(1, trademark.getName());
			ps.setInt(2, trademark.getId());
			int res=ps.executeUpdate();			
			ps.close();
			if(res>0){
				return trademark;
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
	public boolean deleteTrademark(String id) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(DELETE_SQL);
			ps.setString(1, id);
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
	
	private PreparedStatement prepareParams(Connection conn, String name) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();		
		if(name!=null && name.length()>0){		
			where.add(" name like ? ");
			params.add(name+"%");
		}	
		
		if(where.size()>0){
			sql+=" WHERE "+String.join(" AND ", where);
		}
		sql+=" ORDER BY name";
		
		return JDBCUtil.getPreparedStatement(conn, sql , params);			
	}	

}
