package br.org.catolicasc.manhe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.org.catolicasc.manhe.enumeration.Status;

@Entity
public class MedicalRecommendation implements Bean{

	@Id
	@GeneratedValue
	private Long id;
	
	private String doctorName;
	
	private Date datetime;
	
	private String description;
	
	private Status status;
	
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public void inactivateMedicalRecommendation(){
		this.status = Status.INACTIVE;
	}
}
