package AgroApp_BackEnd.vendas.dto.entrada;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IngressoSolidario {

    private String tipoAlimento;

    private LocalDate dataDefinida;

}
