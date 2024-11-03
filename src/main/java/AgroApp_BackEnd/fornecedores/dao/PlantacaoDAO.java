package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;

import java.util.List;

public interface PlantacaoDAO {

    void save(PlantacaoEntity plantacaoEntity);

    List<PlantacaoEntity> findAllByIdVendaIsNotNull();

    List<PlantacaoEntity> findAllByFinalizadoAndIdVenda(Boolean finalizado, Long idVenda);

    List<PlantacaoEntity> findAllById(List<Long> idPlantacao);

    List<PlantacaoEntity> findAll();

}
