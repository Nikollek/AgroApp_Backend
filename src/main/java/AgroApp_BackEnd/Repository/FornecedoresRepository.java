package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.Repository.entity.FornecedoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedoresRepository extends JpaRepository<FornecedoresEntity, Long> {

    FornecedoresEntity findByIdPessoaFisica(String idPessoaFisica);

    FornecedoresEntity findByIdPessoaJuridica(String cnpj);
}
