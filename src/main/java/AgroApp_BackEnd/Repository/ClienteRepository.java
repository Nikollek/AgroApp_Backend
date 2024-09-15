package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.entity.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <ClientesEntity, Long> {

    Optional<ClientesEntity> findByEmailClienteAndSenhaCliente(String email, String senha);
}
