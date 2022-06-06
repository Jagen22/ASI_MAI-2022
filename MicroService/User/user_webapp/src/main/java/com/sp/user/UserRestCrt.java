package com.sp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class UserRestCrt {
	@Autowired
    UserService uService;

    @RequestMapping(method=RequestMethod.POST,value="/user/add")
    public void addUser(@RequestBody User user) {
        if(!uService.addUser(user)) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/user")
    public void saveUser(@RequestBody User user) {
        uService.saveUser(user);
    }
    
    @RequestMapping(method = RequestMethod.GET,value="/user")
    public List<User> listAllUsers() {
        List<User> Luser = uService.findAllUsers();
        return Luser;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/user/{id}")
    public User getUser(@PathVariable String id) {
        User u = uService.getUser(Integer.valueOf(id));
        return u;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/user/verif")
    public boolean verifUser(@RequestParam String pseudo, @RequestParam String mdp) {
        return uService.verifUser(pseudo, mdp);
    }
}