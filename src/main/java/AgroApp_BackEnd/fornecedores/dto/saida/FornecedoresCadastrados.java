package AgroApp_BackEnd.fornecedores.dto.saida;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FornecedoresCadastrados {

    private String tipoPessoa;

    private String nomeFornecedor;

    private String telefone;

    public FornecedoresCadastrados(String tipoPessoa, String nomeFantasia, String telefone) {
        this.nomeFornecedor = nomeFantasia;
        this.tipoPessoa = tipoPessoa;
        this.telefone = telefone;
    }
}
