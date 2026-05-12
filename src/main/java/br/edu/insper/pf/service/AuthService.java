package br.edu.insper.pf.service;

import br.edu.insper.pf.exception.NotFoundException;
import br.edu.insper.pf.exception.UnauthorizedException;
import br.edu.insper.pf.model.Papel;
import br.edu.insper.pf.model.User;
import br.edu.insper.pf.repository.UserRepository;
import org.springframework.stereotype.Service;

// essa classeseria para verficar qse o usuario eh admin

@Service
public class AuthService {
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void validarAdmin(Long userId) {
        if (userId == null) {
            throw new UnauthorizedException("Header X-USER-ID é obrigatório");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Usuário do header não encontrado"));
        if (user.getPapel() != Papel.ADMIN) {
            throw new UnauthorizedException("Apenas usuários ADMIN podem realizar esta ação");
        }
    }
}