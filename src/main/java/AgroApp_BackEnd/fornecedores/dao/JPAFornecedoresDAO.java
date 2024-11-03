package AgroApp_BackEnd.fornecedores.dao;

import AgroApp_BackEnd.Repository.FornecedoresPFRepository;
import AgroApp_BackEnd.Repository.FornecedoresPJRepository;
import AgroApp_BackEnd.Repository.FornecedoresRepository;
import AgroApp_BackEnd.Repository.entity.FornecedoresEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPFEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPJEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JPAFornecedoresDAO implements FornecedoresDAO{

    @Autowired
    FornecedoresPJRepository pjRepository;

    @Autowired
    FornecedoresPFRepository pfRepository;

    @Autowired
    FornecedoresRepository fornecedoresRepository;

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
    public Optional<FornecedoresPFEntity> findByTelefone(String telefone) {
        return pfRepository.findByTelefone(telefone);
    }

    @Override
    public FornecedoresEntity findByIdPessoaFisica(String idPessoaFisisca) {
        return fornecedoresRepository.findByIdPessoaFisica(idPessoaFisisca);
    }

    @Override
    public FornecedoresEntity findByIdPessoaJuridica(String cnpj) {
        return fornecedoresRepository.findByIdPessoaJuridica(cnpj);
    }

    @Override
    public List<FornecedoresEntity> findAll() {
        return fornecedoresRepository.findAll();
    }
}
