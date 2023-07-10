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

    public User createUser(User newUser) {
        // Adicione aqui as validações necessárias antes de salvar o usuário

        User user = new User();

        // Configure os outros campos do usuário com base nos valores do objeto newUser recebido como parâmetro
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRoles(newUser.getRoles());

        return userRepository.save(user);
    }



    public User getUserById(Long id) {
        // Verificar se o ID é válido
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo.");
        }

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
        // Verificar se o usuário existe
        User existingUser = getUserById(id);

        // Atualizar os atributos do usuário existente com os valores do usuário atualizado
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRoles(updatedUser.getRoles());

        // Salvar o usuário atualizado no banco de dados
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        // Verificar se o usuário existe
        User user = getUserById(id);

        // Deletar o usuário do banco de dados
        userRepository.delete(user);
    }
}
