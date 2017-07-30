package com.example.mahechabjj.mahechabjj.Repository;

import com.example.mahechabjj.mahechabjj.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

    User findUserById(String id);

    void deleteUserById(User user);
}
