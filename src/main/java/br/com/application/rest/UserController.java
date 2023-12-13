package br.com.application.rest;


import br.com.application.persistence.dto.UserDto;
import br.com.application.persistence.model.User;
import br.com.application.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("v1/users")
public class UserController {
    @Inject
    UserService service;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id){
        User user = this.service.getUser(id).orElse(new User());
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    public Response listUser(){

        List<User> user = this.service.getUsers();
        return Response.status(Response.Status.OK).entity(user).build();
    }
    @POST
    @Transactional
    public Response addUser(@Valid UserDto userData){
        this.service.addUser(userData);
        return Response.status(Response.Status.CREATED).entity("ok").build();
    }
}