package br.com.application.service;

import br.com.application.persistence.dao.AccountDao;
import br.com.application.persistence.dao.UserDao;
import br.com.application.persistence.dto.AccountDto;
import br.com.application.persistence.model.Account;
import br.com.application.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class AccountService {


    @Inject
    private AccountDao accountDao;

    @Inject
    UserDao userDao;
    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }

    public Optional<Account> getAccountById(Long id) {

        return accountDao.get(id);
    }

    public void createAccount(AccountDto accountData) throws TitularNotFoundException {
        Long titularId = accountData.getTitularId();

        Optional<User> titularOptional = userDao.get(titularId);

        if(titularId == null) {
            throw new TitularNotFoundException("Titular não encontrado.");
        }

        User titular = titularOptional.get();

        Account account = new Account();
        account.setTipoConta(accountData.getTipoConta());
        account.setTitular(titular);
        accountDao.save(account);
    }

    public void updateAccount(Long id, AccountDto accountData) {
        Optional<Account> existingAccount = accountDao.get(id);
        existingAccount.ifPresent(account -> {
            account.setTipoConta(accountData.getTipoConta());


            accountDao.update(account);
        });
    }
    public void deposito(Long id, Double valorDeposito) {
        Optional<Account> optionalAccount = accountDao.get(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            double currentBalance = account.getSaldo();

            if (account.getTipoConta() == Account.TipoContaEnum.CONTA_POUPANCA) {
                double taxa = 0.005;
                double taxaCobrada = valorDeposito * taxa;
                valorDeposito -= taxaCobrada;
            }
            account.setSaldo(currentBalance + valorDeposito);
            accountDao.update(account);
        }

    }


    public void saque(Long id, Double valorsaque) {
        Optional<Account> optionalAccount = accountDao.get(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            double currentBalance = account.getSaldo();
            account.setSaldo(currentBalance - valorsaque);
            accountDao.update(account);
        }
    }



    public void deleteAccount(Long id) {
        accountDao.get(id).ifPresent(accountDao::delete);
    }
}
