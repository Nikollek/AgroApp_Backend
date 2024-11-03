package AgroApp_BackEnd.plantacoes.dto.entrada;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlantacoesProntas {

    private Long idPlantacao;

    private Double valorFinal;
}
