package br.org.catolicasc.manhe.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.org.catolicasc.manhe.enumeration.Status;

@Entity
public class Layette implements Bean{

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String description;
	
	private List<String> recommendations;
	
	private Integer recommendationCounter;
	
	private Status status;
	
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getRecommendations() {
		if(recommendations == null){
			recommendations = new ArrayList<String>();
		}
		return recommendations;
	}

	public void setRecommendations(List<String> recommendations) {
		this.recommendations = recommendations;
	}

	public Integer getRecommendationCounter() {
		if(recommendationCounter == null){
			recommendationCounter = 0;
		}
		return recommendationCounter;
	}

	public void setRecommendationCounter(Integer recommendationCounter) {
		this.recommendationCounter = recommendationCounter;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void inactivateLayette(){
		this.status = Status.INACTIVE;
	}
	
	public void addRecommendation(String recommendation){
		this.recommendations.add(recommendation);
		this.recommendationCounter +=1;
	}
}
