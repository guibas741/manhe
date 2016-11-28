package br.org.catolicasc.manhe.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.org.catolicasc.manhe.dao.JpaDaoFactory;
import br.org.catolicasc.manhe.dao.LayetteDao;
import br.org.catolicasc.manhe.entity.Layette;
import br.org.catolicasc.manhe.exceptions.ExistentRecordException;

@Path("/layette")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class LayetteService {

	private LayetteDao dao = JpaDaoFactory.getInstance().getLayetteDao();
	
	private static final int TAMANHO_PAGINA = 10;

	@GET
	@Path("{id}")
	public Layette findLayette(@PathParam("id") Long id) {
		Layette layette = dao.buscaPorld(id);
		if (layette != null)
			return layette;

		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@GET
	public Layettes findAll(@QueryParam("pagina") int pagina) {
		List<Layette> mrs = dao.listaPaginada(pagina, TAMANHO_PAGINA);
		return new Layettes(mrs);
	}
	
	@POST
	public Response createRecommendations(Layette layette) {
		
		try {
			dao.salva(layette);
		} catch (ExistentRecordException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}

		URI uri = UriBuilder.fromPath("layettes/{id}").build(
				layette.getId());

		return Response.created(uri).entity(layette).build();
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, Layette layette) {
		findLayette(id);
		layette.setId(id);
		dao.atualiza(layette);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		dao.remove(id);
	}

}
