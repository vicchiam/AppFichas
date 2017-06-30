package pcs.language;

public class LanguageBuilder {

	int id;
	String name;
	String path;
	int state;
	
	public static LanguageBuilder lenguage(){
		return new LanguageBuilder();
	}
	
	public LanguageBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public LanguageBuilder withName(String name){
		this.name=name;
		return this;
	}
	
	public LanguageBuilder withPath(String path){
		this.path=path;
		return this;
	}
	
	public LanguageBuilder withState(int state){
		this.state=state;
		return this;
	}
	
	public Language build(){
		return new Language(this);
	}
	
}
