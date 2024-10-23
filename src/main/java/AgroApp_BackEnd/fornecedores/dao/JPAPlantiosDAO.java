package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.PlantiosRepository;
import AgroApp_BackEnd.Repository.entity.PlantiosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPAPlantiosDAO implements PlantiosDAO {

    @Autowired
    private PlantiosRepository plantiosRepository;


    @Override
    public List<PlantiosEntity> retornaListaPlantios() {
        return plantiosRepository.findAll();
    }

}
