package pcs.trademark;

import java.util.Collection;

public interface TrademarkDAO {
	
	public Collection<Trademark> listTrademarks(String name);
	
	public Trademark getTrademark(String id);
	
	public Trademark createTrademark(Trademark trademark);
	
	public Trademark updateTrademark(Trademark trademark);
	
	public boolean deleteTrademark(String id);
	
	public boolean updateTrademarkPath(String id, String path);

}
