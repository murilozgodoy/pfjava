package br.edu.insper.pf.service;

import br.edu.insper.pf.dto.UserRequestDTO;
import br.edu.insper.pf.dto.UserResponseDTO;
import br.edu.insper.pf.exception.NotFoundException;
import br.edu.insper.pf.model.User;
import br.edu.insper.pf.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }
    public UserResponseDTO criar(UserRequestDTO request, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);
        User user = new User();
        user.setNome(request.getNome());
        user.setCpf(request.getCpf());
        user.setPapel(request.getPapel());
        User salvo = userRepository.save(user);

        return new UserResponseDTO(salvo);
    }

    public List<UserResponseDTO> listar() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    public UserResponseDTO buscarPorId(Long id) {
        User user = buscarEntidadePorId(id);

        return new UserResponseDTO(user);
    }
    public UserResponseDTO atualizar(Long id, UserRequestDTO request, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);
        User user = buscarEntidadePorId(id);
        user.setNome(request.getNome());
        user.setCpf(request.getCpf());
        user.setPapel(request.getPapel());
        User atualizado = userRepository.save(user);
        return new UserResponseDTO(atualizado);
    }
    public void deletar(Long id, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);

        User user = buscarEntidadePorId(id);

        userRepository.delete(user);
    }

    public User buscarEntidadePorId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }
}