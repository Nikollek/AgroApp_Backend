package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.Repository.entity.FornecedoresPFEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedoresPFRepository extends JpaRepository<FornecedoresPFEntity, String> {

    Optional<FornecedoresPFEntity> findByCpf(String cpf);

}
