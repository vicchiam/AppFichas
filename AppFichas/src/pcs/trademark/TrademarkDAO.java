package pcs.trademark;

import java.util.Collection;

public interface TrademarkDAO {
	
	public Collection<Trademark> listTrademarks(String name);
	
	public Trademark getTrademark(int id);
	
	public Trademark createTrademark(Trademark trademark);
	
	public Trademark updateTrademark(Trademark trademark);
	
	public boolean deleteTrademark(int id);
	
	public boolean updateTrademarkPath(int id, String path);
	
	public Collection<Trademark> listTrademarksForUser(int idUser);
	
	public Collection<Trademark> listTrademarksForUserNot(int idUser);
	
	public boolean addUserTrademark(int idUser, int idTrademark);
	
	public boolean removeUserTrademark(int idUser, int idTrademark);
	
	

}
