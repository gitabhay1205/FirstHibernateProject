package com.abhay.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS ) // In this parent class table will have its own information only while child class tables will have information of child class as well as their own information
@Inheritance(strategy=InheritanceType.JOINED) //it provide more normalized form than table per class.Parent table will have just its own information while child class tables will have parent id and thier own information
public class Vechile {

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private int vechileId;
	private String vechileName;
	public int getVechileId() {
		return vechileId;
	}
	public void setVechileId(int vechileId) {
		this.vechileId = vechileId;
	}
	public String getVechileName() {
		return vechileName;
	}
	public void setVechileName(String vechileName) {
		this.vechileName = vechileName;
	}
}
