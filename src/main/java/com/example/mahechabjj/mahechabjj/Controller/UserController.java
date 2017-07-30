package com.example.mahechabjj.mahechabjj.Controller;


import com.example.mahechabjj.mahechabjj.Model.User;
import com.example.mahechabjj.mahechabjj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("findById/{id}")
    public User findUserById(@PathVariable("id") String id) {
        return userRepository.findUserById(id);
    }

    @PostMapping("search")
    public PagedListHolder<User> searchUsers(@RequestBody User user) {
        //TODO IMPLEMENT THIS ENDPOINT
        //TODO ADD THE PAGEDLSITHOLDER IMPLEMENTATIOH

        //return userRepository.searchUser(user);
        return null;
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userRepository.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity  editUser(@RequestBody User user, @PathVariable("id") String id) {
        //TODO implement the editing functionality.
        //perhaps with a custom repository function to perform more of the logic and etc.
        return null;
    }
}
