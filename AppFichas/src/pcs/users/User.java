package pcs.users;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public final static String[] typeNames={"Administrador","Usuario","Cliente"};		
	public final static String[] stateNames={"Pasivo","Activo"};
	
	private int id;
	private String user;
	private String mail;
	private String password;
	private int type;
	private int state;
	
	public User(){
		super();
		this.id = 0;
		this.user = "";
		this.mail = "";
		this.password = "";
		this.type=0;
		this.state=0;
	}

	public User(int id, String user, String mail, String password, int type, int state) {
		super();
		this.id = id;
		this.user = user;
		this.mail = mail;
		this.password = password;
		this.type=type;
		this.state=state;
	}
	
	public User(int id, String user, String mail, int type, int state) {
		super();
		this.id = id;
		this.user = user;
		this.mail = mail;
		this.password = "";
		this.type=type;
		this.state=state;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}	
	
	public static User makeUser(ResultSet rs) throws SQLException{		
			int id=rs.getInt("id");
			String user=rs.getString("user");
			String mail=rs.getString("mail");
			int type=rs.getInt("type");
			int state=rs.getInt("state");
			return new User(id,user,mail,type,state);		
	}
	
	public String getTypeName(){
		return User.typeNames[this.type-1];
	}
	
	public String getStateName(){
		return User.stateNames[this.state];
	}
	
}
