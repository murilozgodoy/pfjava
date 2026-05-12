package br.edu.insper.pf.dto;

import br.edu.insper.pf.model.Curso;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private List<UserResponseDTO> usuarios;

    public CursoResponseDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.usuarios = curso.getUsuarios().stream().map(UserResponseDTO::new).toList();
    }
}