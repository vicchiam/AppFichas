package pcs.longitudeUnit;

import pcs.utils.Params;

public class LongitudeUnitBuilder {
	
	int id=Params.EMPTY_ID;
	String name="";
	float conversionToMeters=0;
	int state=Params.ACTIVE;
	
	public static LongitudeUnitBuilder longitudeBuilder(){
		return new LongitudeUnitBuilder();
	}
	
	public LongitudeUnitBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public LongitudeUnitBuilder withName(String name){
		this.name=name;
		return this;
	}
	
	public LongitudeUnitBuilder withState(int state){
		this.state=state;
		return this;
	}
	
	public LongitudeUnit build(){
		return new LongitudeUnit(this);
	}
	

}
