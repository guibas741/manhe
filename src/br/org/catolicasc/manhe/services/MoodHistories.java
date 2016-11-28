package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.MoodHistory;

@XmlRootElement
public class MoodHistories {

	private List<MoodHistory> moodHistories = new ArrayList<MoodHistory>();
	
	public MoodHistories(){
		
	}
	
	public MoodHistories(List<MoodHistory> moodHistories){
		this.moodHistories = moodHistories;
	}

	@XmlTransient
	public List<MoodHistory> getMoodHistories() {
		return moodHistories;
	}

	public void setMoodHistories(List<MoodHistory> moodHistories) {
		this.moodHistories = moodHistories;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (MoodHistory moodHistory : getMoodHistories()) {
			
			Link link = Link.fromPath("mood-history/{id}")
					.rel("moodHistory")
					.title(moodHistory.getId().toString())
					.build(moodHistory.getId());
			
			
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
	
}
