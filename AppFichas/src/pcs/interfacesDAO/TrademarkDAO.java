package pcs.interfacesDAO;

import java.sql.SQLException;
import java.util.Collection;

import pcs.trademark.Trademark;

public interface TrademarkDAO {
	
	public Collection<Trademark> listTrademarks(String name, int state) throws SQLException;
	
	public Trademark getTrademark(int id) throws SQLException;
	
	public Trademark insertTrademark(Trademark trademark) throws SQLException;
	
	public Trademark updateTrademark(Trademark trademark) throws SQLException;
	
	public boolean changeStateTrademark(int id) throws SQLException;
	
	public boolean updateTrademarkPath(int id, String path) throws SQLException;
	
	public Collection<Trademark> listTrademarksForUser(int idUser) throws SQLException;
	
	public Collection<Trademark> listTrademarksForUserNot(int idUser) throws SQLException;
	
	public boolean addUserTrademark(int idUser, int idTrademark) throws SQLException;
	
	public boolean removeUserTrademark(int idUser, int idTrademark) throws SQLException;
	
	

}
