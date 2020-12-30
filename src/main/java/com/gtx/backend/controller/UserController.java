package com.gtx.backend.controller;


import com.gtx.backend.activeMQ.JmsProducer;
import com.gtx.backend.activeMQ.Slf4j;
import com.gtx.backend.dto.UserDTO;
import com.gtx.backend.request.UserDetailsRequestModel;
import com.gtx.backend.response.UserRest;
import com.gtx.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JmsProducer jmsProducer;

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();
        UserDTO userDTO = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDTO,returnValue);
        //log
        jmsProducer.sendMessage(returnValue);

        return returnValue;

    }
//Consumes accepted data in post method as JSON
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        //Creating returnValue
        UserRest returnValue = new UserRest();
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDTO createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "update user called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete was called";
    }
}
