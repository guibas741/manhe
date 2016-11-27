package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.MoodHistory;

public class MoodHistories {

	private List<MoodHistory> moodHistory = new ArrayList<MoodHistory>();
	
	public MoodHistories(){
		
	}
	
	public MoodHistories(List<MoodHistory> moodHistory){
		this.moodHistory = moodHistory;
	}

	@XmlTransient
	public List<MoodHistory> getMoodHistory() {
		return moodHistory;
	}

	public void setMoodHistory(List<MoodHistory> moodHistory) {
		this.moodHistory = moodHistory;
	}
	
}
