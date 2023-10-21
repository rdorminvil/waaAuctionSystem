package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();
    public Optional<User> getById(Long id);
    public Optional<User> getByEmail(String email);
    public User creatUser(User user);
    public void deleteUserById(Long id);
    public User updateUser(Long id, User user);
}
