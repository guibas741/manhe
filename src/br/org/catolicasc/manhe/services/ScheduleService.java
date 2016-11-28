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
import br.org.catolicasc.manhe.dao.ScheduleDao;
import br.org.catolicasc.manhe.entity.Schedule;
import br.org.catolicasc.manhe.exceptions.ExistentRecordException;

@Path("/schedule")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class ScheduleService {

	private ScheduleDao dao = JpaDaoFactory.getInstance().getScheduleDao();
	
	private static final int TAMANHO_PAGINA = 10;

	@GET
	@Path("{id}")
	public Schedule findSchedule(@PathParam("id") Long id) {
		Schedule schedule = dao.buscaPorld(id);
		if (schedule != null)
			return schedule;

		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@GET
	public Schedules findAll(@QueryParam("pagina") int pagina) {
		List<Schedule> mrs = dao.listaPaginada(pagina, TAMANHO_PAGINA);
		return new Schedules(mrs);
	}
	
	@POST
	public Response createRecommendations(Schedule schedule) {
		
		try {
			dao.salva(schedule);
		} catch (ExistentRecordException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}

		URI uri = UriBuilder.fromPath("schedule/{id}").build(
				schedule.getId());

		return Response.created(uri).entity(schedule).build();
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, Schedule schedule) {
		findSchedule(id);
		schedule.setId(id);
		dao.atualiza(schedule);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		dao.remove(id);
	}

}
