package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.PlantacaoRepository;
import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPAPlantacaoDAO implements PlantacaoDAO{

    @Autowired
    private PlantacaoRepository plantacaoRepository;

    @Override
    public void save(PlantacaoEntity plantacaoEntity) {
        plantacaoRepository.save(plantacaoEntity);
    }

    @Override
    public List<PlantacaoEntity> findAllByIdVendaIsNotNull() {
        return plantacaoRepository.findAllByIdVendaIsNotNull();
    }

    @Override
    public List<PlantacaoEntity> findAllByFinalizadoAndIdVenda(Boolean finalizado, Long idVenda) {
        return plantacaoRepository.findAllByFinalizadoAndIdVenda(finalizado, idVenda);
    }

    @Override
    public List<PlantacaoEntity> findAllById(List<Long> idPlantacao) {
        return plantacaoRepository.findAllById(idPlantacao);
    }

    @Override
    public List<PlantacaoEntity> findAll() {
        return plantacaoRepository.findAll();
    }
}
