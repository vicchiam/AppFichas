package pcs.users;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String user;
	private String mail;
	private String password;
	private int type;
	
	public User(){
		super();
		this.id = 0;
		this.user = "";
		this.mail = "";
		this.password = "";
		this.type=0;
	}

	public User(int id, String user, String mail, String password, int type) {
		super();
		this.id = id;
		this.user = user;
		this.mail = mail;
		this.password = password;
		this.type=type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
