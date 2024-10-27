package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.entity.PlantiosEntity;

import java.util.List;

public interface PlantiosDAO {

    List<PlantiosEntity> retornaListaPlantios();

    List<PlantiosEntity> retornaListaPlantiosComId(List<Long> idPlantio);
}
