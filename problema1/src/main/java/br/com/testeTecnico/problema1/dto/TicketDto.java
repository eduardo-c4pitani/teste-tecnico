package br.com.testeTecnico.problema1.dto;

import br.com.testeTecnico.problema1.models.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TicketDto {
    private Map ticketCliente;
    private Map ticketModulo;
    private List<Ticket> ticket;
}
