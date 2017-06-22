package pcs.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class DAO<T> {

	public Collection<T> list(PreparedStatement ps, AutoMake<T> autoMake) throws SQLException{
		Collection<T> list=new ArrayList<>();
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			list.add(autoMake.autoMake(rs));
		}				
		rs.close();
		ps.close();				
		return list;
	}
	
	
	public T get(PreparedStatement ps, AutoMake<T> autoMake) throws SQLException{
		T result=null;		
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			result=autoMake.autoMake(rs);
		}			
		rs.close();
		ps.close();		
		return result;
	}
	
	public int insert(PreparedStatement ps) throws SQLException{
		int last_inserted_id=0;
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
        	last_inserted_id = rs.getInt(1);        	
        }
        rs.close();
		ps.close();
		return last_inserted_id;
	}
	
	public int update(PreparedStatement ps) throws SQLException{
		int res=ps.executeUpdate();			
		ps.close();
		return res;
	}
	
	public boolean change(PreparedStatement ps) throws SQLException{
		int res=ps.executeUpdate();			
		ps.close();
		if(res>0){
			return true;
		}
		return false;
	}
}
