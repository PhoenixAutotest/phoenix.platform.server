package com.surenpi.autotest.phoenix.repository;

import com.surenpi.autotest.phoenix.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByName(String name);
}
