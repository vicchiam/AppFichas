package pcs.users;

import java.util.Collection;

public interface UserDAO {
	
	public User loginUser(String user, String password);
	
	public Collection<User> listUsers(String username, String mail, String type, String state);
	
	public Collection<User> listAllUser();
	
	public User createUser(String user,String email, String password);

}
