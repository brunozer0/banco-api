package br.com.application.rest;


import br.com.application.persistence.dto.AccountDto;
import br.com.application.persistence.dto.DepositDto;
import br.com.application.persistence.dto.SaqueDto;
import br.com.application.persistence.model.Account;
import br.com.application.persistence.model.User;
import br.com.application.service.AccountService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("v1/accounts")

public class AccountController {

    @Inject
    private AccountService accountService;

    @GET
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @GET
    @Path("/{id}")
    public Response getAccountById(@PathParam("id") Long id) {
        return accountService.getAccountById(id)
                .map(account -> Response.ok(account).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build());
    }

    @POST
    public Response createAccount(@Valid AccountDto accountDto)  {

        accountService.createAccount(accountDto);
        return Response.status(Response.Status.CREATED).entity("Conta criada").build();

    }


    @PATCH
    @Path("/{id}/deposit")
    public Response deposit(@PathParam("id") Long id, DepositDto deposito) {
        Optional<Account> accountOptional = this.accountService.getAccountById(id);
        if(!accountOptional.isPresent()) {
           return Response.status(Response.Status.NOT_FOUND).entity("Id não existe").build();
        }
        accountService.deposito(id, deposito.getValor());
        return Response.ok("Depósito realizado com sucesso").build();
    }

    @PATCH
    @Path("/{id}/withdraw")
    public Response saque(@PathParam("id") Long id, SaqueDto saque) {
        Optional<Account> accountOptional = this.accountService.getAccountById(id);

        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            double currentBalance = account.getSaldo();
            if(currentBalance >0){
                accountService.saque(id, saque.getSaque());
                return Response.ok("Saque realizado com sucesso").build();
            }
        }
            return Response.status(Response.Status.BAD_REQUEST).entity("Saldo insuficiente").build();

    }

    @DELETE
    @Path("/{id}/delete")
    public Response deleteAccount(@PathParam("id") Long id) {
        Optional<Account> accountOptional = this.accountService.getAccountById(id);

        if(accountOptional.isPresent()){
            accountService.deleteAccount(id);
            return Response.ok("Conta deletada!").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("Id Conta não existe").build();
        }

    }
}
