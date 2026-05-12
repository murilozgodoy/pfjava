package br.edu.insper.pf.repository;

import br.edu.insper.pf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}