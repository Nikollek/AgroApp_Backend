package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.entity.FornecedoresEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPFEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPJEntity;

import java.util.List;
import java.util.Optional;

public interface FornecedoresDAO {

    void save(FornecedoresPJEntity fornecedoresPJEntity);

    void save(FornecedoresPFEntity fornecedoresPFEntity);

    Optional<FornecedoresPJEntity> findByCnpj(String cnpj);

    Optional<FornecedoresPFEntity> findByTelefone(String telefone);

    FornecedoresEntity findByIdPessoaFisica(String idPessoaFisisca);

    FornecedoresEntity findByIdPessoaJuridica(String cnpj);

    List<FornecedoresEntity> findAll();
}
