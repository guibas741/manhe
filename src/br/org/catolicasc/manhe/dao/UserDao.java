package br.org.catolicasc.manhe.dao;

import java.util.List;

import javax.persistence.Query;

import br.org.catolicasc.manhe.entity.User;

public class UserDao extends JpaDaoBase<User> implements IDao<User>{
	public User autenticar(User user){
		Query query = em.createNamedQuery("User.autenticar").setParameter("username", user.getUsername()).setParameter("password", user.getPassword());
		List<User> users = query.getResultList();
		if (!users.isEmpty())
			return users.get(0);
		return null;
	}
	
	public void remove(Long id) {
		em.getTransaction().begin();
		//revisar banco
		Query query = em.createQuery("DELETE FROM User c WHERE c.id = :id ").setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
}
