package br.org.catolicasc.manhe.dao;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.Mood;


public class MoodDao extends JpaDaoBase<Mood> implements IDao<Mood>{
	
	public void remove(Long id) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM Mood c WHERE c.id = :id ").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
	}
}
