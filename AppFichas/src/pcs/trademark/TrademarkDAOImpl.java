package pcs.trademark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.abstracts.DAO;
import pcs.interfacesDAO.TrademarkDAO;
import pcs.utils.JDBCUtil;

public class TrademarkDAOImpl extends DAO<Trademark> implements TrademarkDAO{
	
	private String SELECT_SQL="SELECT id, name, path, state FROM trademark t  ";
	private String SELECT_ID_SQL="SELECT id, name, path, state FROM trademark WHERE id=?";
	private String INSERT_SQL="INSERT INTO trademark (name,path) VALUES(?,NULL)";
	private String UPDATE_SQL="UPDATE trademark SET name=? WHERE id=?";
	private String UPDATE_STATE_SQL="UPDATE trademark SET state=if(state=0,1,0) WHERE id=?";
	private String UPDATE_PATH_SQL="UPDATE trademark SET path=? WHERE id=?";
	private String SELECT_USER_TRADEMARK_SQL="SELECT t.id, t.name, t.path FROM user_trademark ut LEFT JOIN trademark t ON ut.id_trademark=t.id WHERE ut.id_user=? order by t.name";
	private String SELECT_USER_TRADEMARK_SQL_NOT="SELECT t.id, t.name, t.path FROM trademark t WHERE t.id not in (SELECT id_trademark FROM user_trademark WHERE id_user=?) order by t.name";
	private String INSERT_USER_TRADEMARK_SQL="INSERT INTO user_trademark (id_user,id_trademark) VALUES(?,?)";
	private String DELETE_USER_TRADEMARK_SQL="DELETE FROM user_trademark WHERE id_user=? and id_trademark=?";
	
	public TrademarkDAOImpl() {}

	@Override
	public Collection<Trademark> listTrademarks(String name, int state) {
		Collection<Trademark> listTrademarks=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=this.prepareParams(conn, name, state);				
			listTrademarks=super.list(ps, new Trademark());			
		} catch (SQLException e) {			
			e.printStackTrace();
		}			
		return listTrademarks;
	}

	@Override
	public Trademark getTrademark(int id) {
		Trademark trademark=new Trademark();
		try {
			Connection conn=JDBCUtil.getConnection();									
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1,id);
			trademark=super.get(ps, trademark);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return null;
	}

	@Override
	public Trademark createTrademark(Trademark trademark) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, trademark.getName());					
			int id=super.insert(ps);
			trademark.setId(id);
            return trademark;
			
		} catch (SQLException e) {			
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
			int res=super.update(ps);
			if(res>0){
				return trademark;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public boolean changeStateTrademark(int id) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_STATE_SQL);
			ps.setInt(1, id);
			return super.operation(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	@Override
	public boolean updateTrademarkPath(int id, String path) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_PATH_SQL);
			ps.setString(1, path);
			ps.setInt(2, id);
			return super.operation(ps);		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	@Override
	public Collection<Trademark> listTrademarksForUser(int idUser) {		
		return this.listTrademarksUser(idUser, SELECT_USER_TRADEMARK_SQL);
	}
	
	@Override
	public Collection<Trademark> listTrademarksForUserNot(int idUser) {
		return this.listTrademarksUser(idUser, SELECT_USER_TRADEMARK_SQL_NOT);
	}
	
	private Collection<Trademark> listTrademarksUser(int idUser, String SQL){		
		Collection<Trademark> listTrademarks=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setInt(1, idUser);
			listTrademarks=super.list(ps, new Trademark());			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return listTrademarks;
	}
	
	private PreparedStatement prepareParams(Connection conn, String name, int state) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();		
		where.add(" state=? ");
		params.add(state);
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

	@Override
	public boolean addUserTrademark(int idUser, int idTrademark) {
		return this.userTrademark(idUser, idTrademark, INSERT_USER_TRADEMARK_SQL);
	}

	@Override
	public boolean removeUserTrademark(int idUser, int idTrademark) {
		return this.userTrademark(idUser, idTrademark, DELETE_USER_TRADEMARK_SQL);
	}
	
	private boolean userTrademark(int idUser, int idTrademark, String SQL){
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setInt(1, idUser);
			ps.setInt(2, idTrademark);
			return super.operation(ps);	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}	

}
