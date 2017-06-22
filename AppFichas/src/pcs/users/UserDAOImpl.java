package pcs.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.utils.DAO;
import pcs.utils.JDBCUtil;

public class UserDAOImpl extends DAO<User> implements UserDAO{
	
	private String LOGIN_SQL="SELECT id, user, mail, type, state FROM user WHERE user= ? and password=md5(?) and state=1";
	private String SELECT_SQL="SELECT id, user, mail, type, state FROM user";
	private String SELECT_ID_SQL="SELECT id, user, mail, type, state FROM user WHERE id=?";     
	private String INSERT_SQL="INSERT INTO user (user,password,mail,type) VALUES(?,md5('user'),?,?);";
	private String UPDATE_SQL="UPDATE user SET user=?, mail=?, type=? WHERE id=?";
	private String UPDATE_STATE_SQL="UPDATE user SET state=if(state=0,1,0) WHERE id=?";
	private String UPDATE_PASSWORD_SQL="UPDATE user SET password=md5(?) WHERE id=?";	
    
    public UserDAOImpl(){}

    @Override
	public User loginUser(String userName, String password) {
    	User user=new User();
    	try{
	    	Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(LOGIN_SQL);
			ps.setString(1, userName);
			ps.setString(2, password);
			user=super.get(ps, user);
    	} catch (SQLException e) {    		
    		e.printStackTrace();
    	}
    	return user;
    }
    
    @Override
	public Collection<User> listUsers(String userName, String mail, int type, int state) {		
		Collection<User> listUsers=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps = this.prepareParams(conn, userName, mail, type, state);
			listUsers=super.list(ps, new User());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}
	
	public User getUser(int id) {
		User user=new User();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_ID_SQL);
			ps.setInt(1, id);
			user=super.get(ps, user);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public boolean changePassword(int id, String password) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_PASSWORD_SQL);
			ps.setString(1, password);
			ps.setInt(2, id);
			return super.change(ps);			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	@Override
	public User createUser(User user) {		
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_SQL,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUser());
			ps.setString(2, user.getMail());
			ps.setInt(3, user.getType());			
			int id=super.insert(ps);
			user.setId(id);
            return user;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}		

	@Override
	public User updateUser(User user) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_SQL);
			ps.setString(1, user.getUser());
			ps.setString(2, user.getMail());
			ps.setInt(3, user.getType());
			ps.setInt(4, user.getId());
			int res=super.update(ps);
			if(res>0){
				return user;
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
	public boolean changeStateUser(int id) {
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(UPDATE_STATE_SQL);
			ps.setInt(1, id);
			return super.change(ps);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}	
	
	private PreparedStatement prepareParams(Connection conn, String userName, String mail, int type, int state) throws SQLException{
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
		
		return JDBCUtil.getPreparedStatement(conn, sql , params);			
	}

	

	

}
