package br.org.catolicasc.manhe.dao;

import java.util.List;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.Layette;
//EU DEIXEI TUDO ESCRITO COM "NOME" MAS PODE SER QUE SEJA "NAME"

public class LayetteDao extends JpaDaoBase<Layette> implements IDao<Layette>{
	//busca por nome do item
	public Layette buscaPorNome(String nome){
		Query query = em.createNamedQuery("Layette.buscaPorNome").setParameter("name", nome);
		List<Layette> layettes = query.getResultList();
		if (!layettes.isEmpty())
			return layettes.get(0);
		return null;
	}
	
	public void remove(Long id) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM Layette c WHERE c.id = :id ").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
	}
}
