package pcs.users;

import pcs.utils.Params;

public class UserBuilder {
	
	int id=0;
	String user="";
	String mail="";
	String password="";
	int type=User.TYPE_EMPTY;
	int state=Params.ACTIVE;
	
	public static UserBuilder user(){
		return new UserBuilder();
	}
	
	public UserBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public UserBuilder withUser(String user){
		this.user=user;
		return this;
	}
	
	public UserBuilder withPassword(String password){
		this.password=password;
		return this;
	}
	
	public UserBuilder withMail(String mail){
		this.mail=mail;
		return this;
	}
	
	public UserBuilder withType(int type){
		this.type=type;
		return this;
	}
	
	public UserBuilder withState(int state){
		this.state=state;
		return this;
	}
	
	public User build(){
		return new User(this);
	}

}
