package pcs.pack;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcs.interfaces.AutoMake;
import pcs.interfacesDAO.WeightDAO;
import pcs.weight.Weight;
import pcs.weight.WeightDAOImpl;

public class Pack implements AutoMake<Pack>, Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String description;
	private int width;
	private int height;
	private int depth;
	private Weight weight;
	private int apt;
	private int state;
	
	public Pack() {
		this.id = 0;
		this.description = "";
		this.width = 0;
		this.height = 0;
		this.depth = 0;
		this.weight = null;
		this.apt = 0;
		this.state = 0;
	}
	
	public Pack(int id, String description, int width, int height, int depth, Weight weight, int apt, int state) {
		this.id = id;
		this.description = description;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.weight = weight;
		this.apt = apt;
		this.state=state;
	}
	
	public Pack(int id, String description, int width, int height, int depth, int idWeight, int apt, int state) {
		this.id = id;
		this.description = description;
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		WeightDAO weightDAO=new WeightDAOImpl();
		
		this.weight = weightDAO.getWeight(idWeight);
		this.apt = apt;
		this.state=state;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public int getApt() {
		return apt;
	}

	public void setApt(int apt) {
		this.apt = apt;
	}			
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public Pack autoMake(ResultSet rs) throws SQLException {
		
		int id=rs.getInt("id");
		String description=rs.getString("description");
		int width=rs.getInt("width");
		int height=rs.getInt("height");
		int depth=rs.getInt("depth");
		int apt=rs.getInt("apt");
		int state=rs.getInt("state");
		int id_weight=rs.getInt("id_weight");		
		
		return new Pack(id, description, width, height, depth, id_weight, apt, state);
	}
	
	
}
