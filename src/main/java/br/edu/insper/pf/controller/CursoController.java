package br.edu.insper.pf.controller;

import br.edu.insper.pf.dto.CursoRequestDTO;
import br.edu.insper.pf.dto.CursoResponseDTO;
import br.edu.insper.pf.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    @PostMapping
    public CursoResponseDTO criar(@RequestHeader("X-USER-ID") Long userHeaderId, @RequestBody @Valid CursoRequestDTO request) {
        return cursoService.criar(request, userHeaderId);
    }
    @GetMapping
    public List<CursoResponseDTO> listar() {
        return cursoService.listar();
    }
    @GetMapping("/{id}")
    public CursoResponseDTO buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CursoResponseDTO atualizar(@PathVariable Long id, @RequestHeader("X-USER-ID") Long userHeaderId, @RequestBody @Valid CursoRequestDTO request) {
        return cursoService.atualizar(id, request, userHeaderId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id, @RequestHeader("X-USER-ID") Long userHeaderId) {
        cursoService.deletar(id, userHeaderId);
    }
    @PostMapping("/{cursoId}/usuarios/{usuarioId}")
    public CursoResponseDTO adicionarUsuario(
            @PathVariable Long cursoId,
            @PathVariable Long usuarioId,
            @RequestHeader("X-USER-ID") Long userHeaderId
    ) {
        return cursoService.adicionarUsuario(cursoId, usuarioId, userHeaderId);
    }

    @DeleteMapping("/{cursoId}/usuarios/{usuarioId}")
    public CursoResponseDTO removerUsuario(@PathVariable Long cursoId, @PathVariable Long usuarioId, @RequestHeader("X-USER-ID") Long userHeaderId) {
        return cursoService.removerUsuario(cursoId, usuarioId, userHeaderId);
    }
}