package br.edu.insper.pf.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoRequestDTO {

    @NotBlank(message = "Nome do curso é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição do curso é obrigatória")
    private String descricao;
}