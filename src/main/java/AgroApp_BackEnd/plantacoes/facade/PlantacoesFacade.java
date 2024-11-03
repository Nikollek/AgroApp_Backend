package AgroApp_BackEnd.plantacoes.facade;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import AgroApp_BackEnd.enuns.Fornecimentos;
import AgroApp_BackEnd.plantacoes.dto.entrada.PlantacoesProntas;
import AgroApp_BackEnd.plantacoes.dto.saida.PlantacoesCadastradas;
import AgroApp_BackEnd.plantacoes.service.PlantacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlantacoesFacade {

    @Autowired
    private PlantacoesService plantacoesService;

    public List<PlantacoesCadastradas> deveReetornarPlantacoesCadastradas() {
        List<PlantacaoEntity> plantacaoEntityList = plantacoesService.deveRetornarPlantacoesCadastradas();

        //cria mapa onde a chave é o nome da fruta e o valor é a instancia
        Map<String, PlantacoesCadastradas> plantacoesMap = new HashMap<>();

        // Processa cada PlantacaoEntity
        plantacaoEntityList.forEach(plantacaoEntity -> {
            // Obtém o nome do alimento a partir do código de plantio usando o enum Fornecimentos
            String nomeFruta = Fornecimentos.fromCodigo(plantacaoEntity.getIdPlantios()).name();

            // Obtém ou cria a instância de PlantacoesCadastradas correspondente
            PlantacoesCadastradas plantacoesCadastradas = plantacoesMap.getOrDefault(nomeFruta,
                    new PlantacoesCadastradas(nomeFruta,
                            plantacaoEntity.getDataCadastro(),
                            plantacaoEntity.getMelhorClima().trim(),
                            plantacaoEntity.getMelhorRegiao().trim(),
                            plantacaoEntity.getTempoFinalizacao().trim()));

            // Atualiza a quantidade de frutas
            plantacoesCadastradas.setQuantidade(plantacoesCadastradas.getQuantidade() != null?
                    plantacoesCadastradas.getQuantidade() + 1: 1);

            // Adiciona o id_plantacao à lista de ids
            plantacoesCadastradas.getIds().add(plantacaoEntity.getId());

            // Adiciona/atualiza a instância no mapa
            plantacoesMap.put(nomeFruta, plantacoesCadastradas);
        });
        // Converte o mapa de valores em uma lista e a retorna
        return new ArrayList<>(plantacoesMap.values());
    }

    public void deveDefinirPlantacaoComoPronto(List<PlantacoesProntas> plantacoesProntas) {
        // busca as plantações definidas pelo adm
       List<PlantacaoEntity> plantacaoEntityList = plantacoesService.deveAtualizarAlimentoComoPronto(plantacoesProntas);

       //Itera sobre a lista de plantacoesPronta
       plantacoesProntas.forEach(plantacoesFeitas -> {
           //encontra entidade corresponde ao id de plantacoes prontas
           plantacaoEntityList.stream()
                   .filter(plantacaoEntity -> plantacaoEntity.getId().equals(plantacoesFeitas.getIdPlantacao()))
                   .findFirst()
                   .ifPresent(plantacaoEntity -> {
                       //cria uma nova entidade utilizando o construtor de cópia
                       PlantacaoEntity atualizandoPlantacao = new PlantacaoEntity(plantacaoEntity);

                       //atualiza valor e finalizado
                       atualizandoPlantacao.setValorFinal(plantacoesFeitas.getValorFinal());
                       atualizandoPlantacao.setFinalizado(Boolean.TRUE);

                       //atualiza entidade com valor e indicador finalizado positivo
                       plantacoesService.deveAtualizarRegistroPlantacao(atualizandoPlantacao);
                   });
       });

    }
}
