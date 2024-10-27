package AgroApp_BackEnd.vendas.dao;

import AgroApp_BackEnd.Repository.PlantacaoRepository;
import AgroApp_BackEnd.Repository.VendaAlimentoRepository;
import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import AgroApp_BackEnd.Repository.entity.VendaAlimentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPAVendaAlimentoDAO implements VendaAlimentoDAO{

    @Autowired
    private PlantacaoRepository plantacaoRepository;

    @Autowired
    private VendaAlimentoRepository vendaAlimentoRepository;

    @Override
    public List<PlantacaoEntity> findAllByFinalizadoAndIdVenda(Boolean finalizado, Long idVenda) {
        return plantacaoRepository.findAllByFinalizadoAndIdVenda(finalizado, idVenda);
    }

    @Override
    public List<PlantacaoEntity> findAllById(List<Long> idPlantacao) {
        return plantacaoRepository.findAllById(idPlantacao);
    }

    @Override
    public VendaAlimentoEntity save(VendaAlimentoEntity vendaAlimentoEntity) {
        return vendaAlimentoRepository.save(vendaAlimentoEntity);
    }
}
