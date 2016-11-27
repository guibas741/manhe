package br.org.catolicasc.manhe.dao;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.Schedule;


public class ScheduleDao extends JpaDaoBase<Schedule> implements IDao<Schedule>{
	
	public void remove(Long id) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM Schedule c WHERE c.id = :id ").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
	}
}
