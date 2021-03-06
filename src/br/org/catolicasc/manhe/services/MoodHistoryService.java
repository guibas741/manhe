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
import br.org.catolicasc.manhe.dao.MoodHistoryDao;
import br.org.catolicasc.manhe.entity.MoodHistory;
import br.org.catolicasc.manhe.exceptions.ExistentRecordException;

@Path("/mood-history")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class MoodHistoryService {

	private MoodHistoryDao dao = JpaDaoFactory.getInstance().getMoodHistoryDao();
	
	private static final int TAMANHO_PAGINA = 10;

	@GET
	@Path("{id}")
	public MoodHistory findMoodHistory(@PathParam("id") Long id) {
		MoodHistory mood = dao.buscaPorld(id);
		if (mood != null)
			return mood;

		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@GET
	public MoodHistories findAll(@QueryParam("pagina") int pagina) {
		List<MoodHistory> mrs = dao.listaPaginada(pagina, TAMANHO_PAGINA);
		return new MoodHistories(mrs);
	}
	
	@POST
	public Response createRecommendations(MoodHistory mood) {
		
		try {
			dao.salva(mood);
		} catch (ExistentRecordException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}

		URI uri = UriBuilder.fromPath("medical-history/{id}").build(
				mood.getId());

		return Response.created(uri).entity(mood).build();
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, MoodHistory mood) {
		findMoodHistory(id);
		mood.setId(id);
		dao.atualiza(mood);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		dao.remove(id);
	}

}
