package com.edu.system.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.system.rest.vo.ArticleContent;
import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Article;

@Component
@Path("/articles")
public class ArticleResource {

    private final ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces("application/json")
    public Response getArticlesByCategory(@PathParam("categoryId") Long categoryId) {
        try {
            List<Article> articles = articleService.getArticlesByCategory(categoryId);
            List<ArticleContent> response = articles.stream().map(ArticleContent::from).collect(Collectors.toList());
            return Response.ok().entity(response).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes
    @Produces
    public Response create(ArticleContent articleResponse){
        try {
            return Response.status(Response.Status.CREATED).entity(articleService.create(articleResponse)).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }
}
