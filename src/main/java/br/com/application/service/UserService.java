package br.com.application.service;

import br.com.application.persistence.dao.UserDao;
import br.com.application.persistence.dto.UserDto;
import br.com.application.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;
    public void addUser(UserDto userData) {

        User user = new User();

        user.setName(userData.getName());
        user.setIdade(userData.getAge());
        user.setPhone(userData.getPhone());
        user.setAdress(userData.getAdress());

        this.userDao.save(user);
    }
    public List <User> getUsers(){
        return this.userDao.getAll();
    }

    public Optional<User> getUser(Long id){
        return this.userDao.get(id);
    }
}