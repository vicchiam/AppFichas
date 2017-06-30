package pcs.interfacesDAO;

import java.sql.SQLException;
import java.util.Collection;

import pcs.pack.Pack;

public interface PackDAO {
	
	public Collection<Pack> listPacks(String description, int apt, int state) throws SQLException;
	
	public Pack getPack(int id) throws SQLException;
	
	public Pack insertPack(Pack pack) throws SQLException;
	
	public Pack updatePack(Pack pack) throws SQLException;
	
	public boolean changeStatePack(int id) throws SQLException;
	
	public boolean insertPackWeight(int idPack, int idWeight) throws SQLException;
	
	public boolean deletePackWeight(int idPack, int idWeight) throws SQLException;

}
