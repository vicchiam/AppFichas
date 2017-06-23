package pcs.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AutoMake<T> {
	
	public T autoMake(ResultSet rs)  throws SQLException;
	
}
