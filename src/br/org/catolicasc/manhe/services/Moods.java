package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.Mood;

public class Moods {

	private List<Mood> mood = new ArrayList<Mood>();
	
	public Moods(){
		
	}
	
	public Moods(List<Mood> mood){
		this.mood = mood;
	}

	@XmlTransient
	public List<Mood> getMood() {
		return mood;
	}

	public void setMood(List<Mood> mood) {
		this.mood = mood;
	}
	
}
