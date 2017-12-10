package com.surenpi.autotest.phoenix.service;

import com.surenpi.autotest.phoenix.entity.Role;
import com.surenpi.autotest.phoenix.entity.User;
import com.surenpi.autotest.phoenix.entity.UserIdentity;
import com.surenpi.autotest.phoenix.model.UserDetail;
import com.surenpi.autotest.phoenix.repository.RoleRepository;
import com.surenpi.autotest.phoenix.repository.UserIdentityRepository;
import com.surenpi.autotest.phoenix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DbUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserIdentityRepository userIdentityRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        User user = userRepository.findByName(userName);
        try {
            if(user == null) {
                return null;
            }

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            List<UserIdentity> identityList = userIdentityRepository.findByUserId(user.getId());
            for(UserIdentity identity : identityList)
            {
                Long roleId = identity.getRoleId();
                Role role = roleRepository.findOne(roleId);

                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            UserDetail userDetail = new UserDetail(user.getName(), user.getPassword(), enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    authorities);

            return userDetail;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}