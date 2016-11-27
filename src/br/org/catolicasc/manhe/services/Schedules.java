package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.Schedule;

public class Schedules {

	private List<Schedule> schedule = new ArrayList<Schedule>();
	
	public Schedules(){
		
	}
	
	public Schedules(List<Schedule> schedule){
		this.schedule = schedule;
	}

	@XmlTransient
	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}
	
}
