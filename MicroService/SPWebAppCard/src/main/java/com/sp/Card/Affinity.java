package com.sp.Card;
public enum Affinity {
	EAU("eau", " aquatique"),
	FEU("feu", " de feu"),
	PLANTE("plante"," floral");
	
	private String name;
	private String desc;
	
	Affinity(String name, String desc){
		this.name = name;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
