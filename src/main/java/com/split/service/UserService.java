package com.split.service;

import com.split.annotation.TargetDataSource;
import com.split.dao.UserRepository;
import com.split.entity.User;
import com.split.pojo.DataSourceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @TargetDataSource(DataSourceKey.SLAVE)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @TargetDataSource(DataSourceKey.MASTER)
    public User save(){
        User user = new User("fha", "fjasl", 12);
        User user1 = userRepository.save(user);
        return user1;
    }

}
