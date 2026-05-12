package br.edu.insper.pf.dto;

import br.edu.insper.pf.model.Papel;
import br.edu.insper.pf.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Papel papel;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.cpf = user.getCpf();
        this.papel = user.getPapel();
    }
}