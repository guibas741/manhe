package br.org.catolicasc.manhe.dao;

import java.util.List;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.MedicalRecommendation;


public class MedicalRecommendationDao extends JpaDaoBase<MedicalRecommendation> implements IDao<MedicalRecommendation>{
	
	//busca por nome do médico
	public MedicalRecommendation buscaPorNome(String nome){
		Query query = em.createNamedQuery("MedicalRecommendation.buscaPorNome").setParameter("doctorName", nome);
		List<MedicalRecommendation> recommendations = query.getResultList();
		if (!recommendations.isEmpty())
			return recommendations.get(0);
		return null;
	}
	
	public void remove(Long id) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM MedicalRecommendation c WHERE c.id = :id ").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
	}
}
