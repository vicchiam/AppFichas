package pcs.weight;

public interface WeightDAO {

	public Weight getWeight(int id);
	
	public Weight createWeight(Weight weight);
	
	public Weight updateWeight(Weight weight);
	
	public boolean deleteWeight(int id);
	
}
