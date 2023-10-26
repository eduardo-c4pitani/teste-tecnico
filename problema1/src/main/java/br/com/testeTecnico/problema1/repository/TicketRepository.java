package br.com.testeTecnico.problema1.repository;

import br.com.testeTecnico.problema1.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket save(Ticket ticket);

    @Query(nativeQuery = true, value = "select * from ticket t " +
            "where extract(month from t.data_abertura)=:mes " +
            "and extract(year from t.data_abertura)=:ano")
    List<Ticket> findByMesAno(@Param("mes") Long mes, @Param("ano") Long ano);
}
