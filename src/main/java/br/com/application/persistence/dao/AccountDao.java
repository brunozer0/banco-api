package br.com.application.persistence.dao;

import br.com.application.persistence.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountDao implements Dao<Account> {

    @Inject
    private EntityManager em;

    @Override
    public Optional<Account> get(Long id) {
        try {
            var query = em.createQuery("SELECT a FROM Account a WHERE id = :id", Account.class);
            return Optional.of(query.setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Account> getAll() {
        var query = em.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Account account) {
        em.persist(account);
    }

    @Override
    @Transactional
    public void update(Account account) {
        em.merge(account);
    }

    @Override
    @Transactional
    public void delete(Account account) {
        if (!em.contains(account)) {
            account = em.find(Account.class, account.getId());
        }
        em.remove(account);
    }


}
