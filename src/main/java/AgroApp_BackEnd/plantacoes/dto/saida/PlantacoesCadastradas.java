package AgroApp_BackEnd.plantacoes.dto.saida;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlantacoesCadastradas {

    private Long quantidade;

    private String alimentoCadastrado;

    private LocalDateTime dataCadastro;

    private String clima;

    private String regiao;

    private String tempoFinalizacao;

    private List<Long> ids;


    public PlantacoesCadastradas(String nomeFruta,
                                 LocalDateTime dataCadastro,
                                 String clima,
                                 String regiao,
                                 String tempoFinalizacao) {
        this.alimentoCadastrado = nomeFruta;
        this.dataCadastro = dataCadastro;
        this.clima = clima;
        this.regiao = regiao;
        this.tempoFinalizacao = tempoFinalizacao;
        this.ids = new ArrayList<>();
    }
}
