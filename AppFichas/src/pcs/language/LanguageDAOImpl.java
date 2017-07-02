package pcs.language;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pcs.generic.GenericDAO;
import pcs.interfacesDAO.LanguageDAO;

public class LanguageDAOImpl implements LanguageDAO{
	
	private String SELECT_SQL="SELECT id, name, path, state FROM language WHERE state=?";
	private String SELECT_ID_SQL="SELECT id, name, path, state FROM language WHERE id=?";
	private String INSERT_SQL="INSERT INTO language (name, path) VALUES(?,NULL)";
	private String UPDATE_SQL="UPDATE language SET name=?, path=?, state=? WHERE id=?";
	private String UPDATE_STATE_SQL="UPDATE language SET state=if(state=0,1,0) WHERE id=?";
	private String UPDATE_PATH_SQL="UPDATE language SET path=? WHERE id=?";
		
	private GenericDAO<Language> genericDAO;
	
	public LanguageDAOImpl(){
		this.genericDAO=new GenericDAO<>();
	}

	@Override
	public Collection<Language> listLanguages(String name, int state) throws SQLException {
		return this.filter(name, state);
	}

	@Override
	public Language getLanguage(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.get(SELECT_ID_SQL, params, LanguageBuilder.lenguage().build());
	}

	@Override
	public Language insertLanguage(Language language) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(language.getName());
		return this.genericDAO.insert(INSERT_SQL, params, language);
	}

	@Override
	public Language updateLanguage(Language language) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(language.getName());
		params.add(language.getPath());
		params.add(language.getState());
		return this.genericDAO.update(UPDATE_SQL, params, language);
	}

	@Override
	public boolean changeStateLanguage(int id) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		return this.genericDAO.operation(UPDATE_STATE_SQL, params);
	}
	
	@Override
	public boolean updatePathLanguage(int id, String path) throws SQLException {
		List<Object> params=new ArrayList<>();
		params.add(id);
		params.add(path);
		return this.genericDAO.operation(UPDATE_PATH_SQL, params);
	}
	
	private Collection<Language> filter(String name, int state) throws SQLException{
		String sql=SELECT_SQL;
		List<String> where=new ArrayList<>();
		List<Object> params=new ArrayList<>();		
		where.add(" state=? ");
		params.add(state);
		if(name!=null && name.length()>0){		
			where.add(" name like ? ");
			params.add(name+"%");
		}	
		
		if(where.size()>0){
			sql+=" WHERE "+String.join(" AND ", where);
		}
		sql+=" ORDER BY name";
		
		return this.genericDAO.list(sql, params, LanguageBuilder.lenguage().build());
	}

	

}
