package pcs.trademark;

import pcs.utils.Params;

public class TrademarkBuilder {
	
	int id=Params.EMPTY_ID;
	String name="";
	String path="";
	int state=Params.ACTIVE;
	
	public static TrademarkBuilder trademark(){
		return new TrademarkBuilder();
	}
	
	public TrademarkBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public TrademarkBuilder withName(String name){
		this.name=name;
		return this;
	}
	
	public TrademarkBuilder withPath(String path){
		this.path=path;
		return this;
	}
	
	public TrademarkBuilder withState(int state){
		this.state=state;
		return this;
	}
	
	public Trademark build(){
		return new Trademark(this);
	}

}
