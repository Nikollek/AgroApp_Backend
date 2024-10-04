package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.Repository.entity.FornecedoresPJEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedoresPJRepository extends JpaRepository<FornecedoresPJEntity, String> {

    Optional<FornecedoresPJEntity> findByCnpj(String cnpj);

}
