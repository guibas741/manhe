package br.org.catolicasc.manhe.services;

import java.net.URI;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import br.org.catolicasc.manhe.dao.JpaDaoFactory;
import br.org.catolicasc.manhe.dao.UserDao;
import br.org.catolicasc.manhe.entity.User;

@Path("/user-auth")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class UserAuthService {

	private UserDao dao = JpaDaoFactory.getInstance().getUserDao();
	
	@Resource    // Step 1
	private WebServiceContext wsContext;
	
	@POST
	public Response createUsers(User user) {
		User userAutenticado = dao.autenticar(user);
		MessageContext mc = wsContext.getMessageContext();    // Step 3
		HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
		
		if(userAutenticado != null && session != null){
			session.setAttribute("user", userAutenticado);
		}
		
		URI uri = UriBuilder.fromPath("user/{id}").build(
				userAutenticado.getId());

		return Response.created(uri).entity(userAutenticado).build();
	}
	
}
