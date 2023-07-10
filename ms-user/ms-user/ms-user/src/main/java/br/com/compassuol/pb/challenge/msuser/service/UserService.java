package br.com.compassuol.pb.challenge.msuser.service;

import br.com.compassuol.pb.challenge.msuser.entity.User;
import br.com.compassuol.pb.challenge.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        return createdUser;
    }

    public User getUserById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo.");
        }

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado para o ID: " + id);
        }

        return userOptional.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRoles(updatedUser.getRoles());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);

        userRepository.delete(user);
    }
}
