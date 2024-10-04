package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.FornecedoresPFRepository;
import AgroApp_BackEnd.Repository.FornecedoresPJRepository;
import AgroApp_BackEnd.Repository.entity.FornecedoresPFEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPJEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JPAFornecedoresDAO implements FornecedoresDAO{

    @Autowired
    FornecedoresPJRepository pjRepository;

    @Autowired
    FornecedoresPFRepository pfRepository;

    @Override
    public void save(FornecedoresPJEntity fornecedoresPJEntity) {
        pjRepository.save(fornecedoresPJEntity);
    }

    @Override
    public void save(FornecedoresPFEntity fornecedoresPFEntity) {
        pfRepository.save(fornecedoresPFEntity);
    }

    @Override
    public Optional<FornecedoresPJEntity> findByCnpj(String cnpj) {
        return pjRepository.findByCnpj(cnpj);
    }

    @Override
    public Optional<FornecedoresPFEntity> findByCpf(String cpf) {
        return pfRepository.findByCpf(cpf);
    }
}
