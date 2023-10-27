package br.com.testeTecnico.problema1.repository;

import br.com.testeTecnico.problema1.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    ArrayList<Cliente> findAll();

}
