package AgroApp_BackEnd.vendas.dto.saida;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VendasAlimentosEfetivadas {

    private String nomeAlimento;

    private Long quantidadeVendida;

    private Double totalVendido;

    public VendasAlimentosEfetivadas(String nomeAlimento, Long quantidadeVendida, double totalVendido) {
        this.nomeAlimento = nomeAlimento;
        this.quantidadeVendida = quantidadeVendida;
        this.totalVendido = totalVendido;
    }
}
