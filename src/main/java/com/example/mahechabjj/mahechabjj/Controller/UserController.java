package com.example.mahechabjj.mahechabjj.Controller;


import com.example.mahechabjj.mahechabjj.Model.User;
import com.example.mahechabjj.mahechabjj.Repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user/test")
    public String test(){
        return "yo bitch";
    }

    @GetMapping("user/findById/{id}")
    public User findUserById(@PathVariable("id") String id) {
        return userRepository.findUserById(id);
    }

    @GetMapping("user/findByEmail")
    public ResponseEntity<User> findUserByEmail(HttpServletRequest headers) {
        String email = headers.getHeader("X-Email");
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("user/create")
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
