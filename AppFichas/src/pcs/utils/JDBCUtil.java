package pcs.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JDBCUtil {
	
	private static Connection connection = null;
	private static final Logger log = LogManager.getLogger("JDBCUtil: ");
	
	public static Connection getConnection(){
		BasicConfigurator.configure();
		if(connection==null){
			connection=createConnection();
		}
		return connection;		
	}
	
	private static Connection createConnection(){
		Connection conn=null;
		try{
			InitialContext initContext=new InitialContext();
			Context env=(Context) initContext.lookup("java:comp/env");
			DataSource ds=((DataSource) env.lookup("jdbc/AppFichasDB"));
			conn=ds.getConnection();
		}
		catch(NamingException e){
			log.error("Configure Naming JDBC "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e){
			log.error("Configure JDBC "+e.getMessage());
			e.printStackTrace();
		}
		return conn;		
	}
	
	public static void closeConnection(){
		connection=null;
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn, String sql, List<Object> params) throws SQLException{
		PreparedStatement ps=conn.prepareStatement(sql);
		
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
