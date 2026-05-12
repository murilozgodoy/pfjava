package br.edu.insper.pf.controller;

import br.edu.insper.pf.dto.UserRequestDTO;
import br.edu.insper.pf.dto.UserResponseDTO;
import br.edu.insper.pf.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public UserResponseDTO criar(@RequestHeader("X-USER-ID") Long userHeaderId, @RequestBody @Valid UserRequestDTO request
    ) {
        return userService.criar(request, userHeaderId);
    }
    @GetMapping
    public List<UserResponseDTO> listar() {
        return userService.listar();
    }
    @GetMapping("/{id}")
    public UserResponseDTO buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }
    @PutMapping("/{id}")
    public UserResponseDTO atualizar(@PathVariable Long id, @RequestHeader("X-USER-ID") Long userHeaderId, @RequestBody @Valid UserRequestDTO request) {
        return userService.atualizar(id, request, userHeaderId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id, @RequestHeader("X-USER-ID") Long userHeaderId) {
        userService.deletar(id, userHeaderId);
    }
}