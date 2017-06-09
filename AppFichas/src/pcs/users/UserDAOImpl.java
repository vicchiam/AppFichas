package pcs.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.utils.JDBCUtil;

public class UserDAOImpl implements UserDAO{
	
	private Connection conn;    
    
    private String SELECT_SQL="SELECT id, user, mail, type FROM user WHERE user= ? and password=md5(?)";
    
    public UserDAOImpl(){
    	conn=JDBCUtil.getConnection();
    }

	@Override
	public User loginUser(String userName, String password) {
		User user=new User();
		try{
			PreparedStatement ps=conn.prepareStatement(SELECT_SQL);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt("id"));
				user.setUser(rs.getString("user"));
				user.setMail(rs.getString("mail"));
				user.setType(rs.getInt("type"));
			}			
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User createUser(String user, String email, String password) {
		return null;
	}

}
