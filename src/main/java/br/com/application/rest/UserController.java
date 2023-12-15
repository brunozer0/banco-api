package br.com.application.rest;


import br.com.application.persistence.dao.AccountDao;
import br.com.application.persistence.dto.AccountDto;
import br.com.application.persistence.dto.UserDto;
import br.com.application.persistence.model.Account;
import br.com.application.persistence.model.User;
import br.com.application.service.AccountService;
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

    @Inject
    AccountDto accountDto;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id){
        Optional<User> userOptional = this.service.getUser(id);

        if (!userOptional.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }
        User user = userOptional.get();
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
        return Response.status(Response.Status.CREATED).entity("Usuário criado").build();
    }


    @PUT
    @Transactional
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, @Valid UserDto userData)  {
        Optional<User> userOptional = service.getUser(id);

        if (!userOptional.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity("id Usuário não encontrado").build();
        }
        service.updateUser(id, userData);
        return Response.ok("Usuário atualizado!").build();
    }


    @DELETE
    @Path("/{id}/delete")
    public Response deleteUser(@PathParam("id") Long id) {
        Optional<User> userOptional = this.service.getUser(id);

        if(!userOptional.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).entity("Id não existe").build();
        }
        service.deleteUser(id);
        return Response.ok("Usuário deletado!").build();
    }
}