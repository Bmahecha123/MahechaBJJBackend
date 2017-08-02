package com.example.mahechabjj.mahechabjj.Repository;

import com.example.mahechabjj.mahechabjj.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{

    User findUserById(String id);

    User findUserByEmail(String email);

    void deleteUserById(User user);
}
