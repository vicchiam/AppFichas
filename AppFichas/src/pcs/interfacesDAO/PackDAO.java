package pcs.interfacesDAO;

import java.util.Collection;

import pcs.pack.Pack;

public interface PackDAO {
	
	public Collection<Pack> listPacks(String description, int state);
	
	public Pack getPack(int id);
	
	public Pack createPack(Pack pack);
	
	public Pack updatePack(Pack pack);
	
	public boolean changeStatePack(int id);

}
