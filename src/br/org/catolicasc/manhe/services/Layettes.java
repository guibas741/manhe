package br.org.catolicasc.manhe.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.manhe.entity.Layette;

public class Layettes {

	private List<Layette> layette = new ArrayList<Layette>();
	
	public Layettes(){
		
	}
	
	public Layettes(List<Layette> layette){
		this.layette = layette;
	}

	@XmlTransient
	public List<Layette> getLayette() {
		return layette;
	}

	public void setLayette(List<Layette> layette) {
		this.layette = layette;
	}
	
}
