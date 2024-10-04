package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.entity.FornecedoresPFEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPJEntity;

import java.util.Optional;

public interface FornecedoresDAO {

    void save(FornecedoresPJEntity fornecedoresPJEntity);

    void save(FornecedoresPFEntity fornecedoresPFEntity);

    Optional<FornecedoresPJEntity> findByCnpj(String cnpj);

    Optional<FornecedoresPFEntity> findByCpf(String cpf);
}
