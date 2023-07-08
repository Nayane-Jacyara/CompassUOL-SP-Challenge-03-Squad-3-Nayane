package br.com.compassuol.pb.challenge.msuser.service;

import br.com.compassuol.pb.challenge.msuser.entity.User;
import br.com.compassuol.pb.challenge.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Adicione aqui as validações necessárias antes de salvar o usuário
        return userRepository.save(user);
    }

    public User getUserById(Long id) {

        // Obter o usuário pelo ID usando o repositório
        Optional<User> userOptional = userRepository.findById(id);

        // Verificar se o usuário existe
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
