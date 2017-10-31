package com.example.mahechabjj.mahechabjj.Controller;


import com.example.mahechabjj.mahechabjj.Model.User;
import com.example.mahechabjj.mahechabjj.Repository.UserRepository;
import java.util.HashMap;
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

  @PostMapping("validate")
  public boolean recoverPassword(@RequestHeader("X-ID") String id, @RequestBody
      String answer) {
    User user = userRepository.findUserById(id);
    boolean match = false;
    String secretQuestion = user.getSecretQuestion();

    if (answer == user.getSecretQuestionAnswer()) {
      match = true;
    }

    return match;
  }
}
