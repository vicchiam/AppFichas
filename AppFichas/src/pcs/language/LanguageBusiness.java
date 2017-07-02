package pcs.language;

import java.sql.SQLException;
import java.util.Collection;

import pcs.interfacesDAO.LanguageDAO;
import pcs.utils.Params;

public class LanguageBusiness {

	private LanguageDAO languageDAO;
	
	public LanguageBusiness(){
		this.languageDAO=new LanguageDAOImpl();
	}
	
	public Collection<Language> listLanguages(String name, int state) throws SQLException{
		return this.languageDAO.listLanguages(name, state);
	}
	
	public Language getLanguage(int id) throws SQLException{
		return this.languageDAO.getLanguage(id);
	}
	
	public Language saveLanguage(int id, String name) throws SQLException{
		Language language=LanguageBuilder
				.lenguage()
				.withId(id)
				.withName(name)
				.build();
		if(language.getId()==Params.EMPTY_ID){
			return this.languageDAO.insertLanguage(language);
		}
		else{
			return this.languageDAO.updateLanguage(language);
		}		
	}
	
	public boolean changeStateLanguage(int id) throws SQLException{
		return this.languageDAO.changeStateLanguage(id);
	}
	
	public boolean changePathLanguage(int id, String path) throws SQLException{
		return this.languageDAO.updatePathLanguage(id, path);
	}
	
}
