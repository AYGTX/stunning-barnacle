package com.gtx.backend.service.impl;

import com.gtx.backend.repositories.UserRepository;
import com.gtx.backend.dto.UserDTO;
import com.gtx.backend.entity.UserEntity;
import com.gtx.backend.service.UserService;
import com.gtx.backend.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserDTO user) {
        //throw existing email error
        if(userRepository.findUserByEmail(user.getEmail())!=null) throw new RuntimeException("email exists");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        //encrypt password before storing it in database
        userEntity.setEncyptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //alphanumeric public secure user id
        String publicUserId = utils.generateUserId(15);
        userEntity.setUserId(publicUserId);
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }



    // Prodiving implementation is needed because of UserService implementation that itself
    // uses UserDetailsService from spring boot security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        //using spring exception to throw if user doesnt exists
        if (userEntity==null){throw new UsernameNotFoundException(email);}
//   public User(String username, String password, Collection<? extends GrantedAuthority> authorities)
        return new User(userEntity.getEmail(),userEntity.getEncyptedPassword(),new ArrayList<>());
    }


    @Override
    public UserDTO getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity==null){throw new UsernameNotFoundException(email);}
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;

    }

    @Override
    public UserDTO getUserByUserId(String id) {
        UserDTO returnValue = new UserDTO();
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity==null){throw new UsernameNotFoundException(id);}
        BeanUtils.copyProperties(userEntity,returnValue);


        return returnValue;
    }

}

