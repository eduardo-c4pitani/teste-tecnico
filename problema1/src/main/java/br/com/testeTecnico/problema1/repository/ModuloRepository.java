package br.com.testeTecnico.problema1.repository;

import br.com.testeTecnico.problema1.models.Cliente;
import br.com.testeTecnico.problema1.models.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    ArrayList<Modulo> findAll();
}
