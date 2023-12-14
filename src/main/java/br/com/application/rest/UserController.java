package br.com.application.rest;


import br.com.application.persistence.dto.AccountDto;
import br.com.application.persistence.dto.UserDto;
import br.com.application.persistence.model.User;
import br.com.application.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("v1/users")
public class UserController {
    @Inject
    UserService service;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id){
//e

        Optional<User> userOptional = this.service.getUser(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Response.status(Response.Status.OK).entity(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }
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
        return Response.status(Response.Status.CREATED).entity("Usuário criado").build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, @Valid UserDto userData)  {
        if(id == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.updateUser(id, userData);
        return Response.ok("Usuário atualizado!").build();
    }


    @DELETE
    @Path("/{id}/delete")
    public Response deleteAccount(@PathParam("id") Long id) {
        Optional<User> userOptional = this.service.getUser(id);
        if(userOptional.isPresent()){
            service.deleteUser(id);
            return Response.ok("Usuário deletado!").build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity("ID Usuário não existe").build();
    }
}