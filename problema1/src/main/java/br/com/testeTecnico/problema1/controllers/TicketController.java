package br.com.testeTecnico.problema1.controllers;

import br.com.testeTecnico.problema1.dto.TicketDto;
import br.com.testeTecnico.problema1.models.Cliente;
import br.com.testeTecnico.problema1.models.Modulo;
import br.com.testeTecnico.problema1.models.Ticket;
import br.com.testeTecnico.problema1.repository.ClienteRepository;
import br.com.testeTecnico.problema1.repository.ModuloRepository;
import br.com.testeTecnico.problema1.repository.TicketRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Inject
    private EntityManager em;
    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private ModuloRepository moduloRepository;
    @Inject
    private TicketRepository ticketRepository;

    @PostMapping("/incluir")
    public ResponseEntity<Ticket>add(@RequestBody Ticket ticket) {
        Ticket t;
        try {
            t = ticketRepository.save(ticket);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/busca/{mes}/{ano}")
    public TicketDto getTicketsMesAno(@PathVariable("mes") Long mes, @PathVariable("ano") Long ano) {
        List<Cliente>clientes = clienteRepository.findAll();
        List<Long>idClientes = clientes.stream().map(cliente -> cliente.getId()).collect(Collectors.toList());
        Map<Long, Long> contagemTicketsPorCliente = new HashMap<>();

        List<Modulo>modulos= moduloRepository.findAll();
        List<Long>idModulos = modulos.stream().map(modulo -> modulo.getId()).collect(Collectors.toList());
        Map<Long, Long> contagemTicketsPorModulo = new HashMap<>();

        List<Ticket>tickets = ticketRepository.findByMesAno(mes,ano);

        for (Long idCliente : idClientes) {
            long contagem = tickets.stream()
                    .filter(ticket -> ticket.getCliente().getId().equals(idCliente))
                    .count();
            contagemTicketsPorCliente.put(idCliente, contagem);
        }

        for (Long idModulo : idModulos) {
            long contagem = tickets.stream()
                    .filter(ticket -> ticket.getModulo().getId().equals(idModulo))
                    .count();
            contagemTicketsPorModulo.put(idModulo, contagem);
        }

        TicketDto ticketDto = new TicketDto();
        ticketDto.setTicketCliente(contagemTicketsPorCliente);
        ticketDto.setTicketModulo(contagemTicketsPorModulo);
        ticketDto.setTicket(tickets);

        return ticketDto;


    }


}
