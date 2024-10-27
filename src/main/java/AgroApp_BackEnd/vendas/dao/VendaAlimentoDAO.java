package AgroApp_BackEnd.vendas.dao;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import AgroApp_BackEnd.Repository.entity.VendaAlimentoEntity;

import java.util.List;

public interface VendaAlimentoDAO {

    List<PlantacaoEntity> findAllByFinalizadoAndIdVenda(Boolean aTrue, Long idVenda);

    List<PlantacaoEntity> findAllById(List<Long> idPlantacao);

    VendaAlimentoEntity save(VendaAlimentoEntity vendaAlimentoEntity);
}
