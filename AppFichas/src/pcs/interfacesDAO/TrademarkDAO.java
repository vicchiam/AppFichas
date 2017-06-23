package pcs.interfacesDAO;

import java.util.Collection;

import pcs.trademark.Trademark;

public interface TrademarkDAO {
	
	public Collection<Trademark> listTrademarks(String name, int state);
	
	public Trademark getTrademark(int id);
	
	public Trademark createTrademark(Trademark trademark);
	
	public Trademark updateTrademark(Trademark trademark);
	
	public boolean changeStateTrademark(int id);
	
	public boolean updateTrademarkPath(int id, String path);
	
	public Collection<Trademark> listTrademarksForUser(int idUser);
	
	public Collection<Trademark> listTrademarksForUserNot(int idUser);
	
	public boolean addUserTrademark(int idUser, int idTrademark);
	
	public boolean removeUserTrademark(int idUser, int idTrademark);
	
	

}
