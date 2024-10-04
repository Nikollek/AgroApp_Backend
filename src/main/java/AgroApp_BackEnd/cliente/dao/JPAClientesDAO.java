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

    @Override
    public Optional<ClientesEntity> findByEmailClienteAndSenhaCliente(String email, String senha) {
        return repository.findByEmailClienteAndSenhaCliente(email, senha);
    }
}
