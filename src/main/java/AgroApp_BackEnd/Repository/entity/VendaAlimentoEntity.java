package AgroApp_BackEnd.Repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "VendaAlimento")
@Entity(name = "VendaAlimento")
public class VendaAlimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Long idVenda;

    @Column(name = "total")
    private Double total;

    @Column(name = "id_cliente")
    private Long idCliente;

    public VendaAlimentoEntity(Double totalVenda, Long idCliente) {
        this.total = totalVenda;
        this.idCliente = idCliente;
    }
}
