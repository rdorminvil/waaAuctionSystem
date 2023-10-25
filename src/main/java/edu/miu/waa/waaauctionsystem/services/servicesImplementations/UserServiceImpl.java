package edu.miu.waa.waaauctionsystem.services.servicesImplementations;

import edu.miu.waa.waaauctionsystem.models.Role;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.repositories.UserRepository;
import edu.miu.waa.waaauctionsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Page<User> getAll(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User creatUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles().contains(Role.SELLER)){
            user.addRole(Role.CUSTOMER);
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> updatedUser=userRepository.findById(id);
        updatedUser.ifPresent(uu -> {
            uu.setName(user.getName());
            uu.setEmail(user.getEmail());
            uu.setPassword(user.getPassword());
            uu.setRoles(user.getRoles());
            uu.setAccountBalance(user.getAccountBalance());
            uu.setProducts(user.getProducts());
            userRepository.save(uu);
        });
        return userRepository.findById(id).orElse(null);
    }
}
