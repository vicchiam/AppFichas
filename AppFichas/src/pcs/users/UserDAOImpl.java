package pcs.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.utils.JDBCUtil;

public class UserDAOImpl implements UserDAO{
	
	private String LOGIN_SQL="SELECT id, user, mail, type, state FROM user WHERE user= ? and password=md5(?) and state=1";
	private String SELECT_SQL="SELECT id, user, mail, type, state FROM user";
        
    
    public UserDAOImpl(){
    	
    }

	@Override
	public User loginUser(String userName, String password) {
		User user=new User();
		try{
			Connection conn=JDBCUtil.getConnection();
			List<Object> params=new ArrayList<>();
			params.add(userName);
			params.add(password);	
			PreparedStatement ps=JDBCUtil.getPreparedStatement(conn, LOGIN_SQL , params);			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user=User.makeUser(rs);
			}			
			rs.close();
			ps.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e){
			JDBCUtil.closeConnection();
			e.printStackTrace();			
		}
		return user;
	}
	
	@Override
	public Collection<User> listUsers(String userName, String mail, String type, String state) {
		Collection<User> listUsers=new ArrayList<>();
		try {
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement ps=this.prepareParams(conn, userName, mail, type, state);				
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				listUsers.add(User.makeUser(rs));
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
		return listUsers;
	}
	
	@Override
	public Collection<User> listAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(String user, String email, String password) {
		return null;
	}	
	
	private PreparedStatement prepareParams(Connection conn, String userName, String mail, String type, String state) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();		
		if(userName!=null && userName.length()>0){		
			where.add(" user like ? ");
			params.add(userName+"%");
		}
		if(mail!=null && mail.length()>0){
			where.add(" mail like ? ");
			params.add(mail);			
		}
		if(type!=null && type.length()>0){
			where.add(" type=? ");
			params.add(type);
		}
		if(state!=null && state.length()>0){
			where.add(" state=? ");
			params.add(state);
		}
		else if(state==null){
			where.add(" state=1 ");			
		}
		
		if(where.size()>0){
			sql+=" WHERE "+String.join(" AND ", where);
		}
		
		System.out.println(sql);
		
		return JDBCUtil.getPreparedStatement(conn, sql , params);			
	}
	
	
	

}
