package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.Layette;

@XmlRootElement
public class Layettes {

	private List<Layette> layettes = new ArrayList<Layette>();
	
	public Layettes(){
		
	}
	
	public Layettes(List<Layette> layettes){
		this.layettes = layettes;
	}

	@XmlTransient
	public List<Layette> getLayettes() {
		return layettes;
	}

	public void setLayettes(List<Layette> layettes) {
		this.layettes = layettes;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Layette layette : getLayettes()) {
			
			Link link = Link.fromPath("layettes/{id}")
					.rel("layette")
					.title(layette.getId().toString())
					.build(layette.getId());
			
			
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
	
}
