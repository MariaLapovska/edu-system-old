package com.edu.system.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.system.rest.vo.TestContent;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.vo.types.TestType;

@Component
@Path("/tests")
public class TestResource {

    private final TestService testService;

    @Autowired
    public TestResource(TestService testService) {
        this.testService = testService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(TestContent content) {
        try {
            return Response.status(Response.Status.CREATED).entity(testService.create(content)).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }

    @POST
    @Path("/validate/{type}/{test_id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response validate(@PathParam("type") TestType testType, @PathParam("test_id") Long testId, String payload) {
        try {
            return Response.ok(testService.validate(testType, testId, payload)).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{test_id}/next")
    @Produces("application/json")
    public Response findNext(@PathParam("test_id") Long testId){
        try {
            return Response.ok(testService.findNext(testId)).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
