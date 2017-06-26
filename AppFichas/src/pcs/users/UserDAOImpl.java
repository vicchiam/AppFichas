package pcs.users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.UserDAO;

public class UserDAOImpl implements UserDAO{
	
	private String LOGIN_SQL="SELECT id, user, mail, type, state FROM user WHERE user= ? and password=md5(?) and state=1";
	private String SELECT_SQL="SELECT id, user, mail, type, state FROM user";
	private String SELECT_ID_SQL="SELECT id, user, mail, type, state FROM user WHERE id=?";     
	private String INSERT_SQL="INSERT INTO user (user,password,mail,type) VALUES(?,md5('user'),?,?);";
	private String UPDATE_SQL="UPDATE user SET user=?, mail=?, type=? WHERE id=?";
	private String UPDATE_STATE_SQL="UPDATE user SET state=if(state=0,1,0) WHERE id=?";
	private String UPDATE_PASSWORD_SQL="UPDATE user SET password=md5(?) WHERE id=?";	
	
	private GenericDAO<User> genericDAO; 
    
    public UserDAOImpl(){
    	this.genericDAO=new GenericDAO<>();
    }

    @Override
	public User loginUser(String userName, String password) throws SQLException {
    	List<Object> params=new ArrayList<>();
    	params.add(userName);
    	params.add(password);
    	return this.genericDAO.get(LOGIN_SQL, params, new User());
    }
    
    @Override
	public Collection<User> listUsers(String userName, String mail, int type, int state) throws SQLException {		
		return this.filter(userName, mail, type, state);
	}
	
	public User getUser(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, new User());
	}
	
	@Override
	public boolean changePassword(int id, String password) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(password);
		params.add(id);
		return this.genericDAO.operation(UPDATE_PASSWORD_SQL, params);
	}
	
	@Override
	public User insertUser(User user) throws SQLException {		
		List<Object> params=new ArrayList<>();
		params.add(user.getUser());
		params.add(user.getMail());
		params.add(user.getType());
		return this.genericDAO.insert(INSERT_SQL, params, user);
	}		

	@Override
	public User updateUser(User user) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(user.getUser());
		params.add(user.getMail());
		params.add(user.getType());
		params.add(user.getId());
		return this.genericDAO.update(UPDATE_SQL, params, user);
	}

	@Override
	public boolean changeStateUser(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(UPDATE_STATE_SQL, params);
	}	
	
	private Collection<User> filter(String userName, String mail, int type, int state) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();
		
		where.add(" state=? ");
		params.add(state);
		if(userName!=null && userName.length()>0){		
			where.add(" user like ? ");
			params.add(userName+"%");
		}
		if(mail!=null && mail.length()>0){
			where.add(" mail like ? ");
			params.add(mail+"%");			
		}
		if(type>0){
			where.add(" type=? ");
			params.add(type);
		}		
		
		if(where.size()>0){
			sql+=" WHERE "+String.join(" AND ", where);
		}
		sql+=" ORDER BY user";
		
		return this.genericDAO.list(sql, params, new User());		
	}

	

	

}
