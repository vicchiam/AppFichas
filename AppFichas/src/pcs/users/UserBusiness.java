package pcs.users;

import java.util.Collection;

import org.json.simple.JSONArray;


public class UserBusiness {
	
	private UserDAO userDAO;
	
	public UserBusiness(){
		this.userDAO=new UserDAOImpl();
	}
	
	public User login(String user, String password){
		return this.userDAO.loginUser(user, password);
	}
	
	public Collection<User> listUsers(String user, String mail, int type, int state){		
		return this.userDAO.listUsers(user, mail, type, state);
	}
	
	public User getUser(int id){
		return this.userDAO.getUser(id);
	}
	
	public User saveUser(int id, String userName, String mail, int type){
		User user=new User();
		user.setId(id);
		user.setUser(userName);
		user.setMail(mail);
		user.setType(type);	
		if(user.getId()==0){
			user=this.userDAO.createUser(user);
		}
		else{
			user=this.userDAO.updateUser(user);
		}		
		return user;
	}
	
	public boolean changeStateUser(int id){
		return this.userDAO.changeStateUser(id);
	}
	
	public boolean savePassword(int id, String password){
		return this.userDAO.changePassword(id, password);
	}
	
	@SuppressWarnings("unchecked")
	public String listUsersAutocomplete(String userName, String mail, int type, int state){
		JSONArray root = new JSONArray();
		
		Collection<User> listUsers=this.userDAO.listUsers(userName, mail, type, state);
		for(User user : listUsers){
			root.add(user.getUser());
		}		
		return root.toJSONString();		
	}
	
	@SuppressWarnings("unchecked")
	public String listMailsAutocomplete(String userName, String mail, int type, int state){
		JSONArray root = new JSONArray();
		
		Collection<User> listUsers=this.userDAO.listUsers(userName, mail, type, state);
		for(User user : listUsers){
			root.add(user.getMail());
		}		
		return root.toJSONString();		
	}

}
