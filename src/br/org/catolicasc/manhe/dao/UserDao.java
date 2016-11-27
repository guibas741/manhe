package br.org.catolicasc.manhe.dao;

import java.util.List;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.User;

public class UserDao extends JpaDaoBase<User> implements IDao<User>{
	public User buscaPorNome(String nome){
		Query query = em.createNamedQuery("User.buscaPorNome").setParameter("nome", nome);
		List<User> users = query.getResultList();
		if (!users.isEmpty())
			return users.get(0);
		return null;
	}
	
	public void remove(String nome) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM User c WHERE c.nome = :nome ").setParameter("nome", nome);
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
}
