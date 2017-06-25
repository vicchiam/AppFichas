package pcs.abstracts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import pcs.utils.JDBCUtil;

public class GenericDAO<T extends Generic<T>> {
	
	private Connection conn;
	private PreparedStatement ps;
	
	public GenericDAO(){
		this.conn=JDBCUtil.getConnection();		
	}
	
	public T insert(String SQL, List<Object> params, T generic) throws SQLException{
		int last_inserted_id=0;
		ps=this.getPreparedStatement(SQL, params);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
        	last_inserted_id = rs.getInt(1);        	
        }
        rs.close();
		ps.close();
		if(last_inserted_id>0){
			generic.setId(last_inserted_id);
			return generic;		
		}
		else{
			throw new SQLException("Insert empty");
		}
	}
	
	public T update(String SQL, List<Object> params, T generic) throws SQLException{
		ps=this.getPreparedStatement(SQL, params);
		int res=ps.executeUpdate();
		ps.close();
		if(res>0){
			return generic;
		}
		throw new SQLException("Update empty");
	}
	
	public T get(String SQL, List<Object> params, T generic) throws SQLException{
		ps=this.getPreparedStatement(SQL, params);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			generic=generic.autoMake(rs);
		}			
		rs.close();
		ps.close();
		return generic;		
	}
	
	public Collection<T> list(String SQL, List<Object> params, T generic) throws SQLException{
		Collection<T> list=new ArrayList<T>();
		ps=this.getPreparedStatement(SQL, params);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			list.add(generic.autoMake(rs));
		}				
		rs.close();
		ps.close();				
		return list;
	}
	
	public boolean operation(String SQL, List<Object> params) throws SQLException{
		ps=this.getPreparedStatement(SQL, params);
		int res=ps.executeUpdate();			
		ps.close();
		if(res>0){
			return true;
		}
		return false;
	}
	
	private PreparedStatement getPreparedStatement(String SQL, List<Object> params) throws SQLException{
		PreparedStatement ps=conn.prepareStatement(SQL);
		
		int i=1;
		for(Object param: params){
			if(param instanceof Date){
				i++;
			}
			else if(param instanceof Integer){
				ps.setInt(i++, ((Integer)param));
			}
			else if(param instanceof Long){
				ps.setLong(i++,((Long)param));
			}
			else if(param instanceof Float){
				ps.setFloat(i++,((Float)param));
			}
			else if(param instanceof Double){
				ps.setDouble(i++,((Double)param));
			}
			else{
				ps.setString(i++,((String)param));
			}			
		}		
		
		return ps;
	}
	
	
}
