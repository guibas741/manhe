package br.org.catolicasc.manhe.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.org.catolicasc.manhe.dao.JpaDaoFactory;
import br.org.catolicasc.manhe.dao.MedicalRecommendationDao;
import br.org.catolicasc.manhe.entity.MedicalRecommendation;
import br.org.catolicasc.manhe.exceptions.ExistentRecordException;

public class MedicalRecommendationService {

	private MedicalRecommendationDao dao = JpaDaoFactory.getInstance().getMedicalRecommendationDao();
	
	private static final int TAMANHO_PAGINA = 10;

	@GET
	@Path("{id}")
	public MedicalRecommendation findMedicalRecommendation(@PathParam("id") Long id) {
		MedicalRecommendation medicalRecommendation = dao.buscaPorld(id);
		if (medicalRecommendation != null)
			return medicalRecommendation;

		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@GET
	public MedicalRecommendations findAll(@QueryParam("pagina") int pagina) {
		List<MedicalRecommendation> mrs = dao.listaPaginada(pagina, TAMANHO_PAGINA);
		return new MedicalRecommendations(mrs);
	}
	
	@POST
	public Response createRecommendations(MedicalRecommendation medicalRecommendation) {
		
		try {
			dao.salva(medicalRecommendation);
		} catch (ExistentRecordException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}

		URI uri = UriBuilder.fromPath("medical-recommendation/{id}").build(
				medicalRecommendation.getId());

		return Response.created(uri).entity(medicalRecommendation).build();
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, MedicalRecommendation medicalRecommendation) {
		findMedicalRecommendation(id);
		medicalRecommendation.setId(id);
		dao.atualiza(medicalRecommendation);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		dao.remove(id);
	}

}
