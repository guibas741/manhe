package br.org.catolicasc.manhe.dao;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.MoodHistory;


public class MoodHistoryDao extends JpaDaoBase<MoodHistory> implements IDao<MoodHistory>{
	
	public void remove(Long id) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM MoodHistory c WHERE c.id = :id ").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
	}
}
