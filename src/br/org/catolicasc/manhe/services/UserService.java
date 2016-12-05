package br.org.catolicasc.manhe.services;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.org.catolicasc.manhe.dao.JpaDaoFactory;
import br.org.catolicasc.manhe.dao.UserDao;
import br.org.catolicasc.manhe.entity.User;
import br.org.catolicasc.manhe.exceptions.ExistentRecordException;

@Path("/user")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class UserService {

	private UserDao dao = JpaDaoFactory.getInstance().getUserDao();
	
	@GET
	@Path("{id}")
	public User findUser(@PathParam("id") Long id) {
		User user = dao.buscaPorld(id);
		if (user != null)
			return user;

		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@POST
	public Response createUsers(User user) {
		
		try {
			dao.salva(user);
		} catch (ExistentRecordException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}

		URI uri = UriBuilder.fromPath("user/{id}").build(
				user.getId());

		return Response.created(uri).entity(user).build();
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, User user) {
		findUser(id);
		user.setId(id);
		dao.atualiza(user);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		dao.remove(id);
	}

}
