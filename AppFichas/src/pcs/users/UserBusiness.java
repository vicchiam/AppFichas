package pcs.users;

import java.sql.SQLException;
import java.util.Collection;

import org.json.simple.JSONArray;

import pcs.interfacesDAO.UserDAO;
import pcs.utils.Params;

public class UserBusiness {
	
	private UserDAO userDAO;
	
	public UserBusiness(){
		this.userDAO=new UserDAOImpl();
	}
	
	public User login(String user, String password) throws SQLException{
		return this.userDAO.loginUser(user, password);
	}
	
	public Collection<User> listUsers(String user, String mail, int type, int state) throws SQLException{		
		return this.userDAO.listUsers(user, mail, type, state);
	}
	
	public User getUser(int id) throws SQLException{
		return this.userDAO.getUser(id);
	}
	
	public User saveUser(int id, String userName, String mail, int type) throws SQLException{
		User user=UserBuilder.user()
				.withId(id)
				.withUser(userName)
				.withMail(mail)
				.withType(type)
				.build();
		if(user.getId()==Params.EMPTY_ID){
			user=this.userDAO.insertUser(user);
		}
		else{
			user=this.userDAO.updateUser(user);
		}		
		return user;
	}
	
	public boolean changeStateUser(int id) throws SQLException{
		return this.userDAO.changeStateUser(id);
	}
	
	public boolean savePassword(int id, String password) throws SQLException{
		return this.userDAO.changePassword(id, password);
	}
	
	@SuppressWarnings("unchecked")
	public String autocompleteUser(String userName, String mail, int type, int state) throws SQLException{
		JSONArray root = new JSONArray();
		
		Collection<User> listUsers=this.userDAO.listUsers(userName, mail, type, state);
		for(User user : listUsers){
			root.add(user.getUser());
		}		
		return root.toJSONString();		
	}
	
	@SuppressWarnings("unchecked")
	public String autocompleteMail(String userName, String mail, int type, int state) throws SQLException{
		JSONArray root = new JSONArray();
		
		Collection<User> listUsers=this.userDAO.listUsers(userName, mail, type, state);
		for(User user : listUsers){
			root.add(user.getMail());
		}		
		return root.toJSONString();		
	}

}
