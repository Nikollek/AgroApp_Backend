package AgroApp_BackEnd.Repository.entity;

import AgroApp_BackEnd.integracao.openIA.dto.saida.InformacaoAlimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Plantacao")
@Entity(name = "Plantacao")
public class PlantacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plantacao")
    private Long id;

    @Column(name = "regiao")
    private String melhorRegiao;

    @Column(name = "clima")
    private String melhorClima;

    @Column(name = "id_fornecedor")
    private Integer idFornecedor;

    @Column(name = "id_plantios")
    private Long idPlantios;

    @Column(name = "valor_final")
    private Double valorFinal;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "tempo_finalizacao")
    private String tempoFinalizacao;

    @Column(name = "id_venda")
    private Long idVenda;

    public PlantacaoEntity(InformacaoAlimento informacaoAlimento, Long idPlantios, Integer idFornecedor, Long quantidade) {
        this.melhorRegiao = informacaoAlimento.getMelhorRegiao();
        this.melhorClima = informacaoAlimento.getClima();
        this.idFornecedor = idFornecedor;
        this.idPlantios = idPlantios;
        this.quantidade = quantidade;
        this.finalizado = Boolean.FALSE;
        this.tempoFinalizacao = informacaoAlimento.getTempoFinalizacao();
    }
}
