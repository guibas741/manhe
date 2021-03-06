package br.org.catolicasc.manhe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.org.catolicasc.manhe.enumeration.Status;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Layette implements Bean{

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String description;
	
	private Status status;
	
	//private User user;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
	
	public void inactivateLayette(){
		this.status = Status.INACTIVE;
	}
	
}
