package com.tweetapp.service;

import com.tweetapp.exception.TweetAppException;
import com.tweetapp.model.utilityModel.ChangePassword;
import com.tweetapp.model.utilityModel.LoginModel;
import com.tweetapp.model.Users;
import com.tweetapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users users) throws TweetAppException {
        if(users == null){
            throw new TweetAppException("User passed is null");
        }
        if(usersRepository.findByEmail(users.getEmail()).isPresent()){
            throw new TweetAppException("Email already exists");
        }
        users.setUsername(users.getFirstName().toLowerCase().substring(0,3)+users.getLastName().toLowerCase().substring(0,3)+users.getContactNumber().substring(0,4));
        if(usersRepository.findByUsername(users.getUsername()).isPresent()){
            throw new TweetAppException("Username already exists");
        }
        return usersRepository.saveAndFlush(users);
    }

    public Users login(LoginModel user) throws TweetAppException {
        Optional<Users> users = usersRepository.findByEmail(user.getEmail());
        if(users.isEmpty())
            throw new TweetAppException("Email address not present");
        Users u = users.get();
        return u.getPassword().equals(user.getPassword())?u:null;
    }

    public Users updatePassword(ChangePassword cp,String username) throws TweetAppException {
        if(usersRepository.findByUsername(username).isEmpty())
            throw new TweetAppException("Username is not found");

        Users users = usersRepository.findByUsername(username).get();
        if(!users.getPassword().equals(cp.getOldPassword()))
            throw new TweetAppException("Username is not found");

        users.setPassword(cp.getNewPassword());
        return usersRepository.saveAndFlush(users);
    }

    public boolean usernameIsEmpty(String username){
        return usersRepository.findByUsername(username).isEmpty();
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public List<Users> getUserByRegex(String username) throws TweetAppException {
        return usersRepository.findByUsernameContains(username);
    }

    public Users getUserByUsername(String username) throws TweetAppException {
        if(usernameIsEmpty(username))
            throw new TweetAppException("Username Invalid");
        return usersRepository.findByUsername(username).get();
    }

    public List<Users> getAllUsersInList(List<String> usernames){
        return usersRepository.findByUsernameIn(usernames);
    }
}
