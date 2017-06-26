package pcs.interfacesDAO;

import java.sql.SQLException;
import java.util.Collection;

import pcs.users.User;

public interface UserDAO {
	
	public User loginUser(String user, String password) throws SQLException;
	
	public boolean changePassword(int id, String password) throws SQLException;
	
	public Collection<User> listUsers(String username, String mail, int type, int state) throws SQLException;
	
	public User getUser(int id) throws SQLException;
	
	public User insertUser(User user) throws SQLException;
	
	public User updateUser(User user) throws SQLException;
	
	public boolean changeStateUser(int id) throws SQLException;	

}
