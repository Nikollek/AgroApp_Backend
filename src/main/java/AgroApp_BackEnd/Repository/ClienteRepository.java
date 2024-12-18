package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.Repository.entity.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <ClientesEntity, Long> {

    Optional<ClientesEntity> findByEmailCliente(String email);

    Optional<ClientesEntity> findByEmailClienteAndSenhaCliente(String emailUsuario, String senhaCliente);
}
