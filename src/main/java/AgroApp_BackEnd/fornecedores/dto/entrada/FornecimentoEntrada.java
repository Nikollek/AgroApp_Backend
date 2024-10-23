package AgroApp_BackEnd.fornecedores.dto.entrada;

import AgroApp_BackEnd.enuns.Fornecimentos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecimentoEntrada {

    private Fornecimentos tipoFornecimento;
    private Long quantidade;

}
