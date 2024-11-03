package AgroApp_BackEnd.vendas.dto.saida;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Relatorios {

    private Long totalIngressos;

    private Long totalVendaAlimentos;

    private Double ganhoTotal;

    private Map<String, Double> mapTopCincoAlimentosFornecidos;

    private Map<String, Double> mapTopCincoAlimentosIngresso;


}
