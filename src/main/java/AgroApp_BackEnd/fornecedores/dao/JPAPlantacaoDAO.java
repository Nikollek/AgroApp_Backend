package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.PlantacaoRepository;
import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JPAPlantacaoDAO implements PlantacaoDAO{

    @Autowired
    private PlantacaoRepository plantacaoRepository;

    @Override
    public void save(PlantacaoEntity plantacaoEntity) {
        plantacaoRepository.save(plantacaoEntity);
    }
}
