package org.pvronlineService.activity.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pvronlineAPI.Activity;
import org.pvronlineModel.AuthBean;
import org.pvronlineModel.MailBean;
import org.pvronlineModel.PvrDetailsBean;
import org.pvronlineModel.UserBean;
import org.pvronlineService.activity.bussines.AllUsers;
import org.pvronlineService.activity.bussines.CreateUser;
import org.pvronlineService.activity.bussines.Login;
import org.pvronlineService.activity.bussines.MailIds;
import org.pvronlineService.activity.bussines.PvrDetails;
import org.pvronlineService.activity.bussines.SendEmail;
import org.pvronlineService.activity.bussines.SignUp;

@Path("/activity")
@Produces("application/json")
@Consumes("application/json")
public class ActivityImpl implements Activity{

	SignUp signUp = new SignUp();
	Login loginDetail = new Login();
	CreateUser createUser = new CreateUser();
	SendEmail sendEmail = new SendEmail();
	AllUsers allUsers = new AllUsers();
	PvrDetails pvrDetails = new PvrDetails();
	MailIds mailIds = new MailIds();

	@Path("/signup")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response getSignUp(AuthBean signup) {
		int status = signUp.execute(signup);
		if (status == 1) {
			return Response.accepted(MediaType.APPLICATION_JSON).entity("{\"status\":\"success\"}")
					.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		return Response.accepted(MediaType.APPLICATION_JSON).entity("{\"status\":\"fail\"}")
				.status(Status.BAD_REQUEST.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@Path("/login")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response getLogin(AuthBean login) {
		String fullname = loginDetail.execute(login);
		if (fullname.isEmpty() || fullname == null) {
			return Response.accepted(MediaType.APPLICATION_JSON)
					.entity("{\"fullname\":" + fullname + "}")
					.status(Status.BAD_REQUEST.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		return Response.accepted(MediaType.APPLICATION_JSON).entity("{\"fullname\":\"" + fullname + "\"}")
				.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@Path("/adduser")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(UserBean user) {
		UserBean addedUser = createUser.execute(user);
		if (addedUser != null) {
			return Response.accepted(MediaType.APPLICATION_JSON).entity(addedUser)
					.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		return Response.accepted(MediaType.APPLICATION_JSON).entity(addedUser)
				.status(Status.BAD_REQUEST.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@Path("/sendmail")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response sendMail(MailBean mail) {
		sendEmail.sendMail(mail.getTo(), mail.getSubject(), mail.getMessage());
		return Response.accepted(MediaType.APPLICATION_JSON).entity("{\"status\":\"sent\"}")
				.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@Path("/allUsers")
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAllUsers() {
		List<UserBean> userList = allUsers.execute();
		if (!userList.isEmpty()) {
			return Response.accepted(MediaType.APPLICATION_JSON).entity(userList)
					.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		return Response.accepted(MediaType.APPLICATION_JSON).entity(userList)
				.status(Status.BAD_REQUEST.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}

	@Path("/pvrdetails")
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response getPvrDetails() {
		List<PvrDetailsBean> listPvr = pvrDetails.execute();
		if (!listPvr.isEmpty()) {
			return Response.accepted(MediaType.APPLICATION_JSON).entity(listPvr)
					.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		return Response.accepted(MediaType.APPLICATION_JSON).entity(listPvr)
				.status(Status.BAD_REQUEST.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
	
	@Path("/movie")
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response getMailIds(@QueryParam("movieCode") int movieCode) {
		List<String> mailIdList = mailIds.execute(movieCode);
		if(!mailIdList.isEmpty()) {
			return Response.accepted(MediaType.APPLICATION_JSON).entity(mailIdList)
					.status(Status.OK.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
		}
		return Response.accepted(MediaType.APPLICATION_JSON).entity(mailIdList)
				.status(Status.BAD_REQUEST.getStatusCode()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").allow("OPTIONS").build();
	}
}
