package AgroApp_BackEnd.Repository.entity;

import AgroApp_BackEnd.vendas.dto.entrada.IngressoSolidario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "VendaVisita")
@Entity(name = "VendaVisita")
public class VendaVisitaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Long idVisita;

    @Column(name = "alimento_ingresso")
    private String alimentoIngresso;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "data_visita")
    private LocalDate dataVisita;

    public VendaVisitaEntity(IngressoSolidario ingressoSolidario, Long idCliente) {

        this.alimentoIngresso = ingressoSolidario.getTipoAlimento();
        this.dataVisita = ingressoSolidario.getDataDefinida();
        this.idCliente = idCliente;
    }
}
