package AgroApp_BackEnd.fornecedores.dto.entrada;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FornecedoresPJEntrada {

    private String cnpj;

    private String razaoSocial;

    private String nomeFantasia;

    private String telefone;

    private String bairro;

    private String cep;

    private String municipio;

    private String uf;

}
