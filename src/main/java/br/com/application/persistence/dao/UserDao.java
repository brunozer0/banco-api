package br.com.application.persistence.dao;

import br.com.application.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDao implements Dao<User> {

    @Inject
    EntityManager em;

    @Override
    public Optional<User> get(Long id) {

        try {
            var query = this.em.createQuery("SELECT u FROM User u where id= :id", User.class);

            return Optional.of( query.setParameter("id", id).getSingleResult());
        }catch(Exception e){
            return Optional. empty();
        }


        //   return this.em.find(User.class, id); funciona//
    }

    @Override
    public List<User> getAll(){

        var query = this.em.createQuery("SELECT u FROM User u", User.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(User user) {

        this.em.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {

        this.em.persist(user);

    }

    @Override
    public void delete(User user) {

    }
}
