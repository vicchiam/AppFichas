package pcs.pack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.PackDAO;

public class PackDAOImpl implements PackDAO{
	
	private String SELECT_SQL="SELECT id, description, mesure, apt, state FROM pack";
	private String SELECT_ID_SQL="SELECT id, description, mesure, apt, state FROM pack WHERE id=?";
	private String INSERT_SQL="INSERT INTO pack (description, mesure, apt) VALUES(?,?,?)";
	private String UPDATE_SQL="UPDATE pack SET description=?, mesure=?, apt=? WHERE id=?";
	private String UPDATE_STATE_SQL="UPDATE pack SET state=if(state=0,1,0) WHERE id=?";

	private GenericDAO<Pack> genericDAO;
	
	public PackDAOImpl(){
		this.genericDAO=new GenericDAO<>();
	}
	
	@Override
	public Collection<Pack> listPacks(String description, int apt, int state) throws SQLException {
		return this.filter(description, apt, state);
	}

	@Override
	public Pack getPack(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, PackBuilder.pack().build());
	}

	@Override
	public Pack insertPack(Pack pack) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(pack.getDescription());
		params.add(pack.getMesure());		
		params.add(pack.getApt());
		return this.genericDAO.insert(INSERT_SQL, params,pack);
	}

	@Override
	public Pack updatePack(Pack pack) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(pack.getDescription());
		params.add(pack.getMesure());		
		params.add(pack.getApt());
		params.add(pack.getId());
		return this.genericDAO.update(UPDATE_SQL, params, pack);
	}

	@Override
	public boolean changeStatePack(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(UPDATE_STATE_SQL, params);
	}

	private Collection<Pack> filter(String description, int apt, int state) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();
		
		where.add(" state=? ");
		params.add(state);
		if(description!=null && description.length()>0){		
			where.add(" description like ? ");
			params.add(description+"%");
		}	
		if(apt!=Pack.APT_EMPTY){
			where.add(" apt=? ");
			params.add(apt);
		}
		
		if(where.size()>0){
			sql+=" WHERE "+String.join(" AND ", where);
		}
		sql+=" ORDER BY description";
		return this.genericDAO.list(sql, params, PackBuilder.pack().build());					
	}
	
	
}
