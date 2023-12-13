package br.com.application.persistence.dto;

import br.com.application.persistence.model.Account;
import br.com.application.persistence.model.User;
import lombok.Data;

@Data
public class AccountDto {

    private Long id;
    private Account.TipoContaEnum tipoConta;
    private Long titularId;
    private UserDto accountData;


    private Double saldo;

    public AccountDto(Account.TipoContaEnum tipoConta, Long titularId, Double saldo, UserDto accountData) {
        this.tipoConta = tipoConta;
        this.titularId = titularId;
        this.saldo = saldo;
        this.accountData = accountData;
    }

}
