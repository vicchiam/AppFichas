package pcs.users;

public interface UserDAO {
	
	public User loginUser(String user, String password);
	
	public User createUser(String user,String email, String password);

}
