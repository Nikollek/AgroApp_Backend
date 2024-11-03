package AgroApp_BackEnd.Repository.entity;

import AgroApp_BackEnd.integracao.openIA.dto.saida.InformacaoAlimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "tempo_finalizacao")
    private String tempoFinalizacao;

    @Column(name = "id_venda")
    private Long idVenda;

    public PlantacaoEntity(InformacaoAlimento informacaoAlimento, Long idPlantios, Integer idFornecedor) {
        this.melhorRegiao = informacaoAlimento.getMelhorRegiao();
        this.melhorClima = informacaoAlimento.getClima();
        this.idFornecedor = idFornecedor;
        this.idPlantios = idPlantios;
        this.finalizado = Boolean.FALSE;
        this.tempoFinalizacao = informacaoAlimento.getTempoFinalizacao();
        this.dataCadastro = LocalDateTime.now();
    }

    // Construtor de cópia
    public PlantacaoEntity(PlantacaoEntity entity) {
        this.id = entity.getId();
        this.melhorRegiao = entity.getMelhorRegiao();
        this.melhorClima = entity.getMelhorClima();
        this.idFornecedor = entity.getIdFornecedor();
        this.idPlantios = entity.getIdPlantios();
        this.dataCadastro = entity.getDataCadastro();
        this.tempoFinalizacao = entity.getTempoFinalizacao();
        this.idVenda = entity.getIdVenda();
    }

}
