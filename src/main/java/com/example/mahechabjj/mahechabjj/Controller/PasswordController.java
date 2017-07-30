package com.example.mahechabjj.mahechabjj.Controller;


import com.example.mahechabjj.mahechabjj.Model.User;
import com.example.mahechabjj.mahechabjj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("password")
public class PasswordController {

  UserRepository userRepository;

  @Autowired
  public PasswordController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("recover/{id}")
  public List<String> getSecretQuestions(@PathVariable("id") String id) {
    User user = userRepository.findUserById(id);

    Map<String, String> secretQuestions = user.getSecretQuestions();

    List<String> secretQuestionList = new ArrayList<>(secretQuestions.keySet());

    return secretQuestionList;
  }

  @PostMapping("recover/{id}")
  public Map<String, String> getPassword(@RequestBody Map<String, String> secretQuestions,
      @PathVariable("id") String id) {
    return null;
    //TODO IMPLEMENT!!!! CHECK FOR EQUALITY OF QUESTIONS TO THE USER LOOKED UP...
    // IF THERES A MATCH THEN SEND PASSWORD IN HASHMAP..
  }
}
