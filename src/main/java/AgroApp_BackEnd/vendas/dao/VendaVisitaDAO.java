package AgroApp_BackEnd.vendas.dao;

import AgroApp_BackEnd.Repository.entity.VendaVisitaEntity;

import java.util.List;

public interface VendaVisitaDAO {

    void save(VendaVisitaEntity vendaVisitaEntity);

    List<VendaVisitaEntity> findAll();
}
