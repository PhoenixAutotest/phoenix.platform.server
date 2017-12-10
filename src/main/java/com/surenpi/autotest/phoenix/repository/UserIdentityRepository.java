package com.surenpi.autotest.phoenix.repository;

import com.surenpi.autotest.phoenix.entity.UserIdentity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserIdentityRepository extends CrudRepository<UserIdentity, Long>
{
    List<UserIdentity> findByUserId(Long userId);
}
