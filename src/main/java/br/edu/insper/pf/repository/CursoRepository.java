package br.edu.insper.pf.repository;

import br.edu.insper.pf.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}