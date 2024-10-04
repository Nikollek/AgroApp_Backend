package AgroApp_BackEnd.cliente.dao;

import AgroApp_BackEnd.Repository.entity.ClientesEntity;

import java.util.Optional;

public interface ClienteDAO {

    void save(ClientesEntity clientesEntity);

    Optional<ClientesEntity> findByEmailClienteAndSenhaCliente(String email, String senha);

}
