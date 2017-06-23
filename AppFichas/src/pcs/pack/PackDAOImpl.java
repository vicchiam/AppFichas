package pcs.pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.abstracts.DAO;
import pcs.interfacesDAO.PackDAO;
import pcs.utils.JDBCUtil;

public class PackDAOImpl extends DAO<Pack> implements PackDAO{
	
	private String SELECT_SQL="SELECT id, description, width, height, depth, id_weight, apt, state FROM pack";
	private String SELECT_ID_SQL="SELECT id, description, width, height, depth, id_weight, apt, state FROM pack WHERE id=?";
	private String INSERT_SQL="INSERT INTO pack (description, width, height, depth, id_weight, apt) VALUES(?,?,?,?,?,?)";
	private String UPDATE_SQL="UPDATE pack SET description=?, width=?, height=?, depth=?, id_weight=?, apt=? WHERE id=?";
	private String UPDATE_STATE_SQL="UPDATE pack SET state=if(state=0,1,0) WHERE id=?";

	@Override
	public Collection<Pack> listPacks(String description, int state) {
		Collection<Pack> listPacks=new ArrayList<>();
		try{
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=this.prepareParams(conn, description, state);
			listPacks=super.list(ps, new Pack());			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listPacks;
	}

	@Override
	public Pack getPack(int id) {
		Pack pack=new Pack();
		try{
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1, id);
			pack=super.get(ps, pack);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return pack;
	}

	@Override
	public Pack createPack(Pack pack) {
		try{
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL);
			ps.setString(1, pack.getDescription());
			ps.setInt(1,pack.getWidth());
			ps.setInt(2, pack.getHeight());
			ps.setInt(3, pack.getDepth());
			ps.setInt(4, pack.getWeight().getId());
			ps.setLong(5, pack.getApt());			
			int id=super.insert(ps);
			pack.setId(id);
			return pack;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return pack;
	}

	@Override
	public Pack updatePack(Pack pack) {
		try{
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_SQL);
			ps.setString(1, pack.getDescription());
			ps.setInt(1,pack.getWidth());
			ps.setInt(2, pack.getHeight());
			ps.setInt(3, pack.getDepth());
			ps.setInt(4, pack.getWeight().getId());
			ps.setLong(5, pack.getApt());	
			ps.setInt(6, pack.getId());
			int id=super.update(ps);
			pack.setId(id);
			return pack;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return pack;
	}

	@Override
	public boolean changeStatePack(int id) {
		try{
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_STATE_SQL);
			ps.setInt(1, id);
			return super.operation(ps);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	private PreparedStatement prepareParams(Connection conn, String description, int state) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();
		
		where.add(" state=? ");
		params.add(state);
		if(description!=null && description.length()>0){		
			where.add(" description like ? ");
			params.add(description+"%");
		}	
		
		if(where.size()>0){
			sql+=" WHERE "+String.join(" AND ", where);
		}
		sql+=" ORDER BY description";
		
		return JDBCUtil.getPreparedStatement(conn, sql , params);			
	}
	
	
}
