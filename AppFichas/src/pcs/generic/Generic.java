package pcs.generic;

import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.utils.Params;

public abstract class Generic<T> {
	
	private int id;	
	
	public Generic(){
		this.id=Params.EMPTY_ID;		
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
