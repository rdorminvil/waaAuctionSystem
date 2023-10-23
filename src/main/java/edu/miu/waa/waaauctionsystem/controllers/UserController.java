package edu.miu.waa.waaauctionsystem.controllers;

import edu.miu.waa.waaauctionsystem.libs.ResponseHandler;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseHandler responseHandler;
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id){
        try {
            User user=userService.getById(id).orElse(null);
            return responseHandler.response(user, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email){
        try {
            User user=userService.getByEmail(email).orElse(null);
            return responseHandler.response(user, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        try{
            User createdUser=userService.creatUser(user);
            return responseHandler.response(createdUser, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, "Failed : "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllUsers(Integer page, Integer pageSize){
        try{
            Page<User> users=userService.getAll(page, pageSize);
            return responseHandler.response(users, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, "Failed : "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestParam Long id){
        try{
            userService.deleteUserById(id);
            return responseHandler.response(null, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, "Failed : "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
