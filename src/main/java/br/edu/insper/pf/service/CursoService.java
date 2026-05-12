package br.edu.insper.pf.service;

import br.edu.insper.pf.dto.CursoRequestDTO;
import br.edu.insper.pf.dto.CursoResponseDTO;
import br.edu.insper.pf.exception.NotFoundException;
import br.edu.insper.pf.model.Curso;
import br.edu.insper.pf.model.User;
import br.edu.insper.pf.repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final UserService userService;
    private final AuthService authService;
    public CursoService(CursoRepository cursoRepository, UserService userService, AuthService authService) {
        this.cursoRepository = cursoRepository;
        this.userService = userService;
        this.authService = authService;
    }

    public CursoResponseDTO criar(CursoRequestDTO request, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);
        Curso curso = new Curso();
        curso.setNome(request.getNome());
        curso.setDescricao(request.getDescricao());
        Curso salvo = cursoRepository.save(curso);
        return new CursoResponseDTO(salvo);
    }

    public List<CursoResponseDTO> listar() {
        return cursoRepository.findAll().stream().map(CursoResponseDTO::new).toList();
    }

    public CursoResponseDTO buscarPorId(Long id) {
        Curso curso = buscarEntidadePorId(id);

        return new CursoResponseDTO(curso);
    }

    public CursoResponseDTO atualizar(Long id, CursoRequestDTO request, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);
        Curso curso = buscarEntidadePorId(id);
        curso.setNome(request.getNome());
        curso.setDescricao(request.getDescricao());
        Curso atualizado = cursoRepository.save(curso);
        return new CursoResponseDTO(atualizado);
    }

    public void deletar(Long id, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);
        Curso curso = buscarEntidadePorId(id);
        cursoRepository.delete(curso);
    }

    public CursoResponseDTO adicionarUsuario(Long cursoId, Long usuarioId, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);
        Curso curso = buscarEntidadePorId(cursoId);
        User user = userService.buscarEntidadePorId(usuarioId);
        if (!curso.getUsuarios().contains(user)) {
            curso.getUsuarios().add(user);
        }
        Curso salvo = cursoRepository.save(curso);
        return new CursoResponseDTO(salvo);
    }

    public CursoResponseDTO removerUsuario(Long cursoId, Long usuarioId, Long userHeaderId) {
        authService.validarAdmin(userHeaderId);

        Curso curso = buscarEntidadePorId(cursoId);
        userService.buscarEntidadePorId(usuarioId);

        curso.getUsuarios().removeIf(user -> user.getId().equals(usuarioId));

        Curso salvo = cursoRepository.save(curso);

        return new CursoResponseDTO(salvo);
    }

    private Curso buscarEntidadePorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
    }
}