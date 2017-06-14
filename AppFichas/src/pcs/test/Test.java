package pcs.test;

import java.util.ArrayList;
import java.util.Collection;

import pcs.trademark.Trademark;
import pcs.users.User;

public class Test {

	public static Collection<User> getUsers(){
		Collection<User> users=new ArrayList<>();
		users.add(new User(1,"Pepe","pepe@mail.com",1,1));
		users.add(new User(2,"Maria","maria@mail.com",2,0));
		users.add(new User(3,"Juan","juan@mail.com",3,1));
		users.add(new User(4,"Luis","luis@mail.com",2,1));
		users.add(new User(5,"Eva","eva@mail.com",1,1));
		return users;
	}
	
	public static Collection<Trademark> getTrademarks(){
		Collection<Trademark> trademmarks=new ArrayList<>();
		trademmarks.add(new Trademark(1, "Cola"));
		trademmarks.add(new Trademark(1, "Pepsi"));
		return trademmarks;
	}
	
}
