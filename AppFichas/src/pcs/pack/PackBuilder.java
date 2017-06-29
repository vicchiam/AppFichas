package pcs.pack;

import java.util.ArrayList;
import java.util.Collection;

import pcs.utils.Params;
import pcs.weight.Weight;

public class PackBuilder {

	int id=Params.EMPTY_ID;
	String description="";
	int apt=Pack.APT_EMPTY;
	int state=Params.ACTIVE;
	String mesure="";
	Collection<Weight> weights=new ArrayList<>();
	
	public static PackBuilder pack(){
		return new PackBuilder();
	}
	
	public PackBuilder withId(int id){
		this.id=id;
		return this;
	}
	
	public PackBuilder withDescription(String description){
		this.description=description;
		return this;
	}
	
	public PackBuilder withApt(int apt){
		this.apt=apt;
		return this;
	}
	
	public PackBuilder withState(int state){
		this.state=state;
		return this;
	}
	
	public PackBuilder withWeights(Collection<Weight> weights){
		this.weights=weights;
		return this;
	}
	
	public PackBuilder withMesure(String mesure){
		this.mesure=mesure;
		return this;
	}
	
	public Pack build(){
		return new Pack(this);
	}
	
}
