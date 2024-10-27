package AgroApp_BackEnd.vendas.service;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import AgroApp_BackEnd.Repository.entity.VendaAlimentoEntity;
import AgroApp_BackEnd.Repository.entity.VendaVisitaEntity;
import AgroApp_BackEnd.vendas.dao.JPAVendaAlimentoDAO;
import AgroApp_BackEnd.vendas.dao.JPAVendaVisitaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendasService {

    @Autowired
    private JPAVendaVisitaDAO jpaVendaVisitaDAO;

    @Autowired
    private JPAVendaAlimentoDAO jpaVendaAlimentoDAO;

    public void salvarCadastroVendaVisita(VendaVisitaEntity vendaVisitaEntity){
        jpaVendaVisitaDAO.save(vendaVisitaEntity);
    }

    public List<PlantacaoEntity> deveRetornanAlimentoPronto(Boolean aTrue) {
        //retorna registros das plantacoes prontas
        return jpaVendaAlimentoDAO.findAllByFinalizadoAndIdVenda(aTrue, null);
    }

    public List<PlantacaoEntity> deveRetornanAlimentoPronto(List<Long> idPlantacao) {
        //retorna registro de Plantacao pelo ID da plantacao
        return jpaVendaAlimentoDAO.findAllById(idPlantacao);
    }

    public VendaAlimentoEntity salvarCadastroVendaAlimento(VendaAlimentoEntity vendaAlimentoEntity) {
        //salva registro da venda do alimento
         return jpaVendaAlimentoDAO.save(vendaAlimentoEntity);
    }
}
