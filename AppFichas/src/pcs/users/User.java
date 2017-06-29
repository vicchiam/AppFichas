package pcs.users;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.generic.Generic;
import pcs.utils.Params;

public class User extends Generic<User> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public final static String[] typeNames={"Administrador","Usuario","Cliente"};			
	public final static int TYPE_EMPTY=0; 
	
	private String user;
	private String mail;
	private String password;
	private int type;
	private int state;
	
	public User(UserBuilder builder){
		super(builder.id);
		this.user=builder.user;
		this.password=builder.password;
		this.mail=builder.mail;
		this.type=builder.type;
		this.state=builder.state;
	}
		
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}	
	
	public String getTypeName(){
		return User.typeNames[this.type-1];
	}
	
	@Override
	public User autoMake(ResultSet rs) throws SQLException {
		int id=rs.getInt("id");
		String user=rs.getString("user");
		String mail=rs.getString("mail");
		int type=rs.getInt("type");
		int state=rs.getInt("state");
		return UserBuilder.user()
			.withId(id)
			.withUser(user)
			.withMail(mail)
			.withType(type)
			.withState(state)
			.build();
	}
	
}
