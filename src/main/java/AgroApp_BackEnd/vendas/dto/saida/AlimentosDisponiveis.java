package AgroApp_BackEnd.vendas.dto.saida;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlimentosDisponiveis {

    private Long idPlantacao;
    private String nomeAlimento;
    private Double valorAlimento;

}
