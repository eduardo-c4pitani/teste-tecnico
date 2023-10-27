package br.com.testeTecnico.problema1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@Table(name = "ticket", schema = "public")
@Entity(name = "id")
@EntityListeners(AuditingEntityListener.class)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("Id")
    private Long id;

    @Column(name = "titulo")
    @JsonProperty("Titulo")
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonProperty("Cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modulo", nullable = false)
    @JsonProperty("modulo")
    private Modulo modulo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_abertura")
    private Date dataAbertura;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_encerramento")
    private Date dataEncerramento;

}
