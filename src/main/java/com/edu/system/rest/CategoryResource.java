package com.edu.system.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.system.service.CategoryService;
import com.edu.system.vo.Category;

@Component
@Path("/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @Produces("application/json")
    public Response getCategories() {
        return Response.ok(categoryService.getAll()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Category category){
        return Response.status(Response.Status.CREATED).entity(categoryService.create(category)).build();
    }

}
