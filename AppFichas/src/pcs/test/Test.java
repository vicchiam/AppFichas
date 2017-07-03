package pcs.test;

import java.util.ArrayList;
import java.util.Collection;

import pcs.language.Language;
import pcs.language.LanguageBuilder;
import pcs.trademark.Trademark;
import pcs.users.User;
import pcs.weightUnit.WeightUnit;

public class Test {

	public static Collection<Language> listLanguages(){
		Collection<Language> list=new ArrayList<>();
		list.add(LanguageBuilder.lenguage().withName("Español").build());
		list.add(LanguageBuilder.lenguage().withName("ingles").build());
		list.add(LanguageBuilder.lenguage().withName("Portugues").build());
		return list;
	}
	
}
