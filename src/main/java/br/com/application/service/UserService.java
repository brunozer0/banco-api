package br.com.application.service;

import br.com.application.persistence.dao.AccountDao;
import br.com.application.persistence.dao.UserDao;
import br.com.application.persistence.dto.AccountDto;
import br.com.application.persistence.dto.UserDto;
import br.com.application.persistence.model.Account;
import br.com.application.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    @Inject
    AccountDao accountDao;
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


    public void deleteUser(Long id) {
        Optional<Account> existingAccount = accountDao.get(id);

        userDao.get(id).ifPresent(userDao::delete);
    }


    public void updateUser(Long id, UserDto userData) {
        Optional<User> existingUser = userDao.get(id);


        User user = existingUser.get();

        if(existingUser.isPresent()){

            user.setName(userData.getName());
            user.setIdade(userData.getAge());
            user.setPhone(userData.getPhone());
            user.setAdress(userData.getAdress());
        }
        userDao.update(user);

    }
}