package AgroApp_BackEnd.vendas.service;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import AgroApp_BackEnd.Repository.entity.VendaAlimentoEntity;
import AgroApp_BackEnd.Repository.entity.VendaVisitaEntity;
import AgroApp_BackEnd.fornecedores.dao.JPAPlantacaoDAO;
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

    @Autowired
    private JPAPlantacaoDAO jpaPlantacaoDAO;

    public void salvarCadastroVendaVisita(VendaVisitaEntity vendaVisitaEntity){
        jpaVendaVisitaDAO.save(vendaVisitaEntity);
    }

    public List<PlantacaoEntity> deveRetornarAlimentoPronto(Boolean aTrue) {
        //retorna registros das plantacoes prontas
        return jpaPlantacaoDAO.findAllByFinalizadoAndIdVenda(aTrue, null);
    }

    public List<PlantacaoEntity> deveRetornarAlimentoPronto(List<Long> idPlantacao) {
        //retorna registro de Plantacao pelo ID da plantacao
        return jpaPlantacaoDAO.findAllById(idPlantacao);
    }

    public VendaAlimentoEntity salvarCadastroVendaAlimento(VendaAlimentoEntity vendaAlimentoEntity) {
        //salva registro da venda do alimento
         return jpaVendaAlimentoDAO.save(vendaAlimentoEntity);
    }

    public List<PlantacaoEntity> deveRetornarAlimentosProntos() {
        return jpaPlantacaoDAO.findAllByIdVendaIsNotNull();
    }

    public List<VendaVisitaEntity> deveRetornarVendasVisitas() {
        return jpaVendaVisitaDAO.findAll();
    }

    public List<VendaAlimentoEntity> deveRetornarVendasAlimentos() {
        return jpaVendaAlimentoDAO.findAll();
    }
}
