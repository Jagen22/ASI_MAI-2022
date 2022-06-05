package enums;

public enum Famille {
	DRAGON("Dragon"),
	AIGLE("Aigle"),
	PIGEON("Pigeon"),
	REQUIN("Requin"),
	TIGRE("Tigre"),
	TORTUE("Tortue"),
	CHIEN("Chien"),
	RAT("Rat"),
	CHEVAL("Cheval"),
	FRELON("Frelon"),
	PARASITE("Parasite"),
	FANTOME("Fantome"),
	ROBOT("Robot"),
	DINOSAURE("Dinosaure");
	
	private String name;
	
	Famille(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
