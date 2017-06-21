package pcs.users;

import java.util.Collection;

import org.json.simple.JSONArray;

import pcs.trademark.Trademark;


public class UserBusiness {
	
	private UserDAO userDAO;
	
	public UserBusiness(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	
	public Collection<User> listUsers(String user, String mail, String type, String state){
		return this.listUsers(user, mail, Integer.parseInt(type), Integer.parseInt(state));
	}
	
	public Collection<User> listUsers(String user, String mail, int type, int state){		
		return this.userDAO.listUsers(user, mail, type, state);
	}
	
	public User getUser(String id){
		return this.getUser(Integer.parseInt(id));
	}
	
	public User getUser(int id){
		return this.userDAO.getUser(id);
	}
	
	public User saveUser(String id, String userName, String mail, String type){
		return this.saveUser(Integer.parseInt(id), userName, mail, Integer.parseInt(type));
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
	
	public boolean changeStateUser(String id){
		return this.changeStateUser(Integer.parseInt(id));
	}
	
	public boolean changeStateUser(int id){
		return this.userDAO.changeStateUser(id);
	}
	
	public boolean savePassword(String id, String password){
		return this.savePassword(Integer.parseInt(id), password);
	}
	
	public boolean savePassword(int id, String password){
		return this.userDAO.changePassword(id, password);
	}
	
	public String listUsersAutocomplete(String userName, String mail, String type, String state){
		return this.listUsersAutocomplete(userName, mail, Integer.parseInt(type), Integer.parseInt(state));
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
	
	public String listMailsAutocomplete(String userName, String mail, String type, String state){
		return this.listMailsAutocomplete(userName, mail, Integer.parseInt(type), Integer.parseInt(state));
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
