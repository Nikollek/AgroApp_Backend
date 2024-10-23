package AgroApp_BackEnd.fornecedores.service;

import AgroApp_BackEnd.Repository.entity.*;
import AgroApp_BackEnd.fornecedores.dao.JPAFornecedoresDAO;
import AgroApp_BackEnd.fornecedores.dao.JPAPlantacaoDAO;
import AgroApp_BackEnd.fornecedores.dao.JPAPlantiosDAO;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPFEntrada;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPJEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FornecedorService {

    @Autowired
    private JPAFornecedoresDAO jpaFornecedoresDAO;

    @Autowired
    private JPAPlantiosDAO jpaPlantiosDAO;

    @Autowired
    private JPAPlantacaoDAO jpaPlantacaoDAO;

    public Optional<FornecedoresPFEntity> retornaFornecedor(FornecedoresPFEntrada fornecedoresPFEntrada){
        //busca se o fornecedor PF existe no banco de dados
        return jpaFornecedoresDAO.findByTelefone(fornecedoresPFEntrada.getTelefone());
    }


    public Optional<FornecedoresPJEntity> retornaFornecedor(FornecedoresPJEntrada fornecedoresPJEntrada){
        //busca se o fornecedor PJ ja existe no banco de dados
        return jpaFornecedoresDAO.findByCnpj(fornecedoresPJEntrada.getCnpj());
    }

    public FornecedoresEntity retornaFornecedor(FornecedoresPFEntity fornecedoresPFEntity){
        //busca entidade fornecedor PF
        return jpaFornecedoresDAO.findByIdPessoaFisica(fornecedoresPFEntity.getTelefone());
    }

    public FornecedoresEntity retornaFornecedor(FornecedoresPJEntity fornecedoresPJEntity){
        //busca entidade fornecedor PJ
        return jpaFornecedoresDAO.findByIdPessoaJuridica(fornecedoresPJEntity.getCnpj());
    }

    //salvar entidade fornecedor PF
    public void salvarFornecedorPF(FornecedoresPFEntity fornecedoresPF){jpaFornecedoresDAO.save(fornecedoresPF);}

    //salvar entidade fornecedor PJ
    public void salvarFornecedorPJ(FornecedoresPJEntity fornecedoresPJ){ jpaFornecedoresDAO.save(fornecedoresPJ); }

    //salvar entidade plantação
    public void salvarPlantacao(PlantacaoEntity plantacaoEntity) {
        jpaPlantacaoDAO.save(plantacaoEntity);
    }

    public List<PlantiosEntity> retornaPlantios (){
        //se não existir ele retorna todos os dados na tabela
        return jpaPlantiosDAO.retornaListaPlantios();
    }


}
