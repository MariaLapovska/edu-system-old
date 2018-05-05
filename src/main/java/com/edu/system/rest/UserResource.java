package com.edu.system.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.system.rest.vo.LoginRequest;
import com.edu.system.service.ServiceException;
import com.edu.system.service.UserService;
import com.edu.system.vo.User;

@Component
@Path("/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getUser(LoginRequest request){
        try {
            String token = userService.authenticate(request.getLogin(), request.getPassword());
            return Response.ok().entity("{\"token\":\"" + token + "\"}").build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createUser(User user){
        try {
            return Response.ok().entity(userService.register(user)).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
