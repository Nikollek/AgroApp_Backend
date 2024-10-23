package AgroApp_BackEnd.integracao.openIA;

import AgroApp_BackEnd.integracao.openIA.dto.saida.InformacaoAlimento;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class OpenAIService {

    public static InformacaoAlimento buscarInformacoesPorNomeAlimento(String jsonData, String nome) {
        // Converte a string JSON em um objeto JSONObject
        JSONObject jsonObject = new JSONObject(jsonData);

        // Extrai o array de alimentos do JSON
        JSONArray alimentos = jsonObject.getJSONArray("alimentos");

        // Itera por cada alimento no array
        for (int i = 0; i < alimentos.length(); i++) {
            // Pega o objeto alimento atual
            JSONObject alimento = alimentos.getJSONObject(i);

            // Verifica se o nome do alimento atual corresponde ao nome buscado (ignorando maiúsculas/minúsculas)
            if (alimento.getString("nome").equalsIgnoreCase(nome)) {
                // Se encontrou o alimento, extrai suas informações (clima, tempo de finalização, melhor região)
                String clima = alimento.getString("clima");
                String tempoFinalizacao = alimento.getString("tempo_finalizacao");
                String melhorRegiao = alimento.getString("melhor_regiao");

                // Retorna um novo objeto InformacaoAlimento com os dados encontrados
                return new InformacaoAlimento(nome, clima, tempoFinalizacao, melhorRegiao);
            }
        }

        // Se nenhum alimento correspondente ao nome foi encontrado, retorna null
        return null;
    }
}
