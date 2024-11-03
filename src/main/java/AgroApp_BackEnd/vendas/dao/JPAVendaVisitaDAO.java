package AgroApp_BackEnd.vendas.dao;

import AgroApp_BackEnd.Repository.VendaVisitaRepository;
import AgroApp_BackEnd.Repository.entity.VendaVisitaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPAVendaVisitaDAO implements VendaVisitaDAO {

    @Autowired
    private VendaVisitaRepository vendaVisitaRepository;

    @Override
    public void save(VendaVisitaEntity vendaVisitaEntity) {
        vendaVisitaRepository.save(vendaVisitaEntity);
    }

    @Override
    public List<VendaVisitaEntity> findAll() {
        return vendaVisitaRepository.findAll();
    }
}
