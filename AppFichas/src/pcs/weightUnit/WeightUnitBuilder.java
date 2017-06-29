package pcs.weightUnit;

import pcs.utils.Params;

public class WeightUnitBuilder {
	
	int id=Params.EMPTY_ID;
	String name="";
	float conversionToKgm;
	int state=Params.ACTIVE;
	
	public static WeightUnitBuilder weightUnit(){
		return new WeightUnitBuilder();
	} 
	
	public WeightUnitBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public WeightUnitBuilder withName(String name){
		this.name=name;
		return this;
	}
	
	public WeightUnitBuilder withConversionToKgm(float conversion){
		this.conversionToKgm=conversion;
		return this;
	}
	
	public WeightUnitBuilder withState(int state){
		this.state=state;
		return this;
	}
	
	public WeightUnit build(){
		return new WeightUnit(this);
	}
	
	

}
