package pcs.trademark;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.TrademarkDAO;

public class TrademarkDAOImpl implements TrademarkDAO{
	
	public static String SELECT_SQL="SELECT id, name, path, state FROM trademark t  ";
	public static String SELECT_ID_SQL="SELECT id, name, path, state FROM trademark WHERE id=?";
	public static String INSERT_SQL="INSERT INTO trademark (name,path) VALUES(?,NULL)";
	public static String UPDATE_SQL="UPDATE trademark SET name=? WHERE id=?";
	public static String UPDATE_STATE_SQL="UPDATE trademark SET state=if(state=0,1,0) WHERE id=?";
	public static String UPDATE_PATH_SQL="UPDATE trademark SET path=? WHERE id=?";
	public static String SELECT_USER_TRADEMARK_SQL="SELECT t.id, t.name, t.path, t.state FROM user_trademark ut LEFT JOIN trademark t ON ut.id_trademark=t.id WHERE t.state=1 AND ut.id_user=? order by t.name";
	public static String SELECT_NOT_USER_TRADEMARK_SQL="SELECT t.id, t.name, t.path, t.state FROM trademark t WHERE t.state=1 AND t.id not in (SELECT id_trademark FROM user_trademark WHERE id_user=?) order by t.name";
	public static String INSERT_USER_TRADEMARK_SQL="INSERT INTO user_trademark (id_user,id_trademark) VALUES(?,?)";
	public static String DELETE_USER_TRADEMARK_SQL="DELETE FROM user_trademark WHERE id_user=? and id_trademark=?";
	
	private GenericDAO<Trademark> genericDAO;
	
	public TrademarkDAOImpl() {
		this.genericDAO=new GenericDAO<>();
	}

	@Override
	public Collection<Trademark> listTrademarks(String name, int state) throws SQLException {
		return filter(name, state);
	}

	@Override
	public Trademark getTrademark(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, TrademarkBuilder.trademark().build());
	}

	@Override
	public Trademark insertTrademark(Trademark trademark) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(trademark.getName());
		return this.genericDAO.insert(INSERT_SQL, params, trademark);		
	}

	@Override
	public Trademark updateTrademark(Trademark trademark) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(trademark.getName());
		params.add(trademark.getId());
		return this.genericDAO.update(UPDATE_SQL, params, trademark);
	}

	@Override
	public boolean changeStateTrademark(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(UPDATE_STATE_SQL, params);
	}
	
	@Override
	public boolean updateTrademarkPath(int id, String path) throws  SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(UPDATE_PATH_SQL, params);
	}
	
	@Override
	public Collection<Trademark> listTrademarksForUser(int idUser) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(idUser);
		return this.genericDAO.list(SELECT_USER_TRADEMARK_SQL, params, TrademarkBuilder.trademark().build());
	}
	
	@Override
	public Collection<Trademark> listTrademarksForUserNot(int idUser) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(idUser);
		return this.genericDAO.list(SELECT_NOT_USER_TRADEMARK_SQL, params, TrademarkBuilder.trademark().build());
	}
	
	@Override
	public boolean addUserTrademark(int idUser, int idTrademark) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(idUser);
		params.add(idTrademark);
		return this.genericDAO.operation(INSERT_USER_TRADEMARK_SQL, params);		
	}

	@Override
	public boolean removeUserTrademark(int idUser, int idTrademark) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(idUser);
		params.add(idTrademark);
		return this.genericDAO.operation(DELETE_USER_TRADEMARK_SQL, params);
	}
	
	private Collection<Trademark> filter(String name, int state) throws SQLException{
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
		
		return this.genericDAO.list(sql, params, TrademarkBuilder.trademark().build());
	}

}
