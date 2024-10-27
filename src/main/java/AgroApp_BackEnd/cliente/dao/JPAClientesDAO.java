package AgroApp_BackEnd.cliente.dao;

import AgroApp_BackEnd.Repository.ClienteRepository;
import AgroApp_BackEnd.Repository.entity.ClientesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JPAClientesDAO implements ClienteDAO {

    @Autowired
    ClienteRepository repository;

    @Override
    public void save(ClientesEntity clientesEntity) {
        repository.save(clientesEntity);
    }

    public Optional<ClientesEntity> findByEmailCliente(String email) {
        return repository.findByEmailCliente(email);
    }

    @Override
    public Optional<ClientesEntity> findByEmailClienteAndSenhaUsuario(String emailUsuario, String senhaCliente) {
        return repository.findByEmailClienteAndSenhaCliente(emailUsuario, senhaCliente);
    }
}
