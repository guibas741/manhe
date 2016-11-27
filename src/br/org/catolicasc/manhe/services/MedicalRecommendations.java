package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.MedicalRecommendation;

public class MedicalRecommendations {

	private List<MedicalRecommendation> medicalRecommendation = new ArrayList<MedicalRecommendation>();
	
	public MedicalRecommendations(){
		
	}
	
	public MedicalRecommendations(List<MedicalRecommendation> medicalRecommendation){
		this.medicalRecommendation = medicalRecommendation;
	}

	@XmlTransient
	public List<MedicalRecommendation> getMedicalRecommendation() {
		return medicalRecommendation;
	}

	public void setMedicalRecommendation(List<MedicalRecommendation> medicalRecommendation) {
		this.medicalRecommendation = medicalRecommendation;
	}
	
}
