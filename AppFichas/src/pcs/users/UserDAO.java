package pcs.users;

import java.util.Collection;

import pcs.trademark.Trademark;

public interface UserDAO {
	
	public User loginUser(String user, String password);
	
	public boolean changePassword(int id, String password);
	
	public Collection<User> listUsers(String username, String mail, int type, int state);
	
	public User getUser(int id);
	
	public User createUser(User user);
	
	public User updateUser(User user);
	
	public boolean changeStateUser(int id);	

}
