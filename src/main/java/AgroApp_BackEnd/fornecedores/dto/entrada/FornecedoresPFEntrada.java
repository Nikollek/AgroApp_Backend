package AgroApp_BackEnd.fornecedores.dto.entrada;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedoresPFEntrada {

    private String telefone;

    private String nome;

    private String idade;

    private String email;

    private FornecimentoEntrada fornecimento;
}
