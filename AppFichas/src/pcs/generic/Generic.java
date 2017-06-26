package pcs.generic;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Generic<T> {

	private int id;
	
	public Generic(){
		this.id=0;
	}
	
	public Generic(int id){
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public abstract T autoMake(ResultSet rs) throws SQLException;
	
	
	
}
