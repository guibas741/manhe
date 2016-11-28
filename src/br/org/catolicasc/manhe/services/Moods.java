package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.Mood;

@XmlRootElement
public class Moods {

	private List<Mood> moods = new ArrayList<Mood>();
	
	public Moods(){
		
	}
	
	public Moods(List<Mood> moods){
		this.moods = moods;
	}

	@XmlTransient
	public List<Mood> getMoods() {
		return moods;
	}

	public void setMood(List<Mood> moods) {
		this.moods = moods;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Mood mood : getMoods()) {
			
			Link link = Link.fromPath("mood/{id}")
					.rel("mood")
					.title(mood.getId().toString())
					.build(mood.getId());
			
			
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
}
