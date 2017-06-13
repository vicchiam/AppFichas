package pcs.users;

import java.util.Collection;

public interface UserDAO {
	
	public User loginUser(String user, String password);
	
	public boolean changePassword(String id, String password);
	
	public Collection<User> listUsers(String username, String mail, String type, String state);
	
	public User getUser(String id);
	
	public User createUser(User user);
	
	public User updateUser(User user);
	
	public boolean changeStateUser(String id);

}
