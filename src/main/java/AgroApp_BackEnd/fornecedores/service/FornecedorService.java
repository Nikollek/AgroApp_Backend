package AgroApp_BackEnd.fornecedores.service;

import AgroApp_BackEnd.Repository.entity.*;
import AgroApp_BackEnd.fornecedores.dao.JPAFornecedoresDAO;
import AgroApp_BackEnd.fornecedores.dao.JPAPlantacaoDAO;
import AgroApp_BackEnd.fornecedores.dao.JPAPlantiosDAO;
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

    public Optional<FornecedoresPFEntity> retornaFornecedorPF(String telefone){
        //busca se o fornecedor PF existe no banco de dados
        return jpaFornecedoresDAO.findByTelefone(telefone);
    }


    public Optional<FornecedoresPJEntity> retornaFornecedorPJ(String cnpj){
        //busca se o fornecedor PJ ja existe no banco de dados
        return jpaFornecedoresDAO.findByCnpj(cnpj);
    }

    public FornecedoresEntity retornaFornecedor(FornecedoresPFEntity fornecedoresPFEntity){
        //busca entidade fornecedor PF
        return jpaFornecedoresDAO.findByIdPessoaFisica(fornecedoresPFEntity.getTelefone());
    }

    public FornecedoresEntity retornaFornecedor(FornecedoresPJEntity fornecedoresPJEntity){
        //busca entidade fornecedor PJ
        return jpaFornecedoresDAO.findByIdPessoaJuridica(fornecedoresPJEntity.getCnpj());
    }

    public List<FornecedoresEntity> retornaFornecedores(){
       return jpaFornecedoresDAO.findAll();
    }

    //salvar entidade fornecedor PF
    public void salvarFornecedorPF(FornecedoresPFEntity fornecedoresPF){jpaFornecedoresDAO.save(fornecedoresPF);}

    //salvar entidade fornecedor PJ
    public void salvarFornecedorPJ(FornecedoresPJEntity fornecedoresPJ){ jpaFornecedoresDAO.save(fornecedoresPJ); }

    //salvar entidade plantação
    public void salvarPlantacao(PlantacaoEntity plantacaoEntity) {
        jpaPlantacaoDAO.save(plantacaoEntity);
    }

    public void salvarPlantacao(List<PlantacaoEntity> plantacaoEntity) {
        plantacaoEntity.forEach(jpaPlantacaoDAO::save);
    }
    public List<PlantiosEntity> retornaPlantios (List<Long> idPlantio){
       // se o id plantios não for nulo então flitra os plantios pelo Id
        if (idPlantio != null){
            return jpaPlantiosDAO.retornaListaPlantiosComId(idPlantio);
        }
        //se não existir ele retorna todos os dados na tabela
        return jpaPlantiosDAO.retornaListaPlantios();
    }


}
