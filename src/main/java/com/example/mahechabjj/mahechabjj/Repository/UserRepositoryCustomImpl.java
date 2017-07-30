package com.example.mahechabjj.mahechabjj.Repository;

import com.example.mahechabjj.mahechabjj.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<User> searchUser(User user) {
        Query searchQuery;
        Criteria criteria;

        if (user.getEmail() != null) {

        }
        if (user.getId() != null) {

        }
        if (user.getName() != null) {

        }
        //TODO implement criteria mongotemplate search return method
        return null;
    }
}
