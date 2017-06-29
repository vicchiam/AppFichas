package pcs.weight;

import pcs.utils.Params;
import pcs.weightUnit.WeightUnit;
import pcs.weightUnit.WeightUnitBuilder;

public class WeightBuilder {

	int id=Params.EMPTY_ID;
	float value;
	WeightUnit weightUnit=WeightUnitBuilder.weightUnit().build();
	
	public static WeightBuilder weight(){
		return new WeightBuilder();
	}
	
	public WeightBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public WeightBuilder withValue(float value){
		this.value=value;
		return this;
	}
	
	public WeightBuilder withWeightUnit(WeightUnit weightUnit){
		this.weightUnit=weightUnit;
		return this;
	}
	
	public Weight build(){
		return new Weight(this);
	}
	
	
}
