package AgroApp_BackEnd.Cliente.Domain.Entrada;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClienteEntrada {
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;

}