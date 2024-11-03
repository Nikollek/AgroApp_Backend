package AgroApp_BackEnd.plantacoes.service;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import AgroApp_BackEnd.fornecedores.dao.JPAPlantacaoDAO;
import AgroApp_BackEnd.plantacoes.dto.entrada.PlantacoesProntas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantacoesService {

    @Autowired
    private JPAPlantacaoDAO jpaPlantacaoDAO;

    public List<PlantacaoEntity> deveRetornarPlantacoesCadastradas() {
        //retorna todos os registros das plantacoes nao finalizadas
        return jpaPlantacaoDAO.findAllByFinalizadoAndIdVenda(Boolean.FALSE, null);
    }

    public List<PlantacaoEntity> deveAtualizarAlimentoComoPronto(List<PlantacoesProntas> plantacoesProntas) {
        //cria lista de ids da plantação
        List<Long> ids = plantacoesProntas.stream().map(plantacoesfeitas ->
                plantacoesfeitas.getIdPlantacao()).toList();

        //busca os registros de plantação com os ids
        return jpaPlantacaoDAO.findAllById(ids);
    }

    public void deveAtualizarRegistroPlantacao(PlantacaoEntity atualizandoPlantacao) {
        //atualiza registro da plantacao
        jpaPlantacaoDAO.save(atualizandoPlantacao);
    }

    public List<PlantacaoEntity> deveRetornarPlantacoes() {
        //busca todos os registros de plantacoes
        return jpaPlantacaoDAO.findAll();
    }
}
