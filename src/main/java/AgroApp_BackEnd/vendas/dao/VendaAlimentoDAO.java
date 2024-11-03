package AgroApp_BackEnd.vendas.dao;

import AgroApp_BackEnd.Repository.entity.VendaAlimentoEntity;

import java.util.List;

public interface VendaAlimentoDAO {

    VendaAlimentoEntity save(VendaAlimentoEntity vendaAlimentoEntity);

    List<VendaAlimentoEntity> findAll();
}
