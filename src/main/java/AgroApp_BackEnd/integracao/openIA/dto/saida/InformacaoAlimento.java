package AgroApp_BackEnd.integracao.openIA.dto.saida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InformacaoAlimento {

    private String nome;
    private String clima;
    private String tempoFinalizacao;
    private String melhorRegiao;
}
