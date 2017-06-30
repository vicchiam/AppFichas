package pcs.interfacesDAO;

import java.sql.SQLException;
import java.util.Collection;

import pcs.language.Language;

public interface LanguageDAO {
	
	public Collection<Language> listLanguages(String name, int state) throws SQLException;
	
	public Language getLanguage(int id) throws SQLException;
	
	public Language insertLanguage(Language language) throws SQLException;
	
	public Language updateLanguage(Language language) throws SQLException;
	
	public boolean changeStateLanguage(int id) throws SQLException;

}
