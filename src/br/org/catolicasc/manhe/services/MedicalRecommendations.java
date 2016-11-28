package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.MedicalRecommendation;

@XmlRootElement
public class MedicalRecommendations {

	private List<MedicalRecommendation> medicalRecommendations = new ArrayList<MedicalRecommendation>();
	
	public MedicalRecommendations(){
		
	}
	
	public MedicalRecommendations(List<MedicalRecommendation> medicalRecommendations){
		this.medicalRecommendations = medicalRecommendations;
	}

	@XmlTransient
	public List<MedicalRecommendation> getMedicalRecommendations() {
		return medicalRecommendations;
	}

	public void setMedicalRecommendations(List<MedicalRecommendation> medicalRecommendations) {
		this.medicalRecommendations = medicalRecommendations;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (MedicalRecommendation medicalRecommendation : getMedicalRecommendations()) {
			
			Link link = Link.fromPath("medical-recommendation/{id}")
					.rel("medicalRecommendation")
					.title(medicalRecommendation.getId().toString())
					.build(medicalRecommendation.getId());
			
			
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
	
}
