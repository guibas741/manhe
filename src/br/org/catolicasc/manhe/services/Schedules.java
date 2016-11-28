package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.Mood;
import br.org.catolicasc.manhe.entity.Schedule;

@XmlRootElement
public class Schedules {

	private List<Schedule> schedules = new ArrayList<Schedule>();
	
	public Schedules(){
		
	}
	
	public Schedules(List<Schedule> schedules){
		this.schedules = schedules;
	}

	@XmlTransient
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Schedule schedule : getSchedules()) {
			
			Link link = Link.fromPath("schedule/{id}")
					.rel("schedule")
					.title(schedule.getId().toString())
					.build(schedule.getId());
			
			
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
}
