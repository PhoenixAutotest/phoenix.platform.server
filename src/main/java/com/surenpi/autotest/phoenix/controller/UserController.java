package com.surenpi.autotest.phoenix.controller;

import com.surenpi.autotest.phoenix.entity.User;
import com.surenpi.autotest.phoenix.model.Response;
import com.surenpi.autotest.phoenix.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(value = "用户管理")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Response<Long> save(@RequestBody User user)
    {
        userRepository.save(user);

        Response<Long> response = new Response<Long>();
        response.setSuccess(true);
        response.setData(user.getId());

        return response;
    }
}