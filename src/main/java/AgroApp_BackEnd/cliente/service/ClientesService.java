package AgroApp_BackEnd.cliente.service;

import AgroApp_BackEnd.Repository.entity.ClientesEntity;
import AgroApp_BackEnd.cliente.dao.JPAClientesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private JPAClientesDAO jpaClientesDAO;

    public Optional<ClientesEntity> buscaCliente(String emailUsuario) {
        //busca registro do cliente pelo email
        return jpaClientesDAO.findByEmailCliente(emailUsuario);
    }

    public Optional<ClientesEntity> buscaCliente(String emailUsuario, String senhaCliente) {
        return jpaClientesDAO.findByEmailClienteAndSenhaUsuario(emailUsuario, senhaCliente);
    }

    public void salvarCliente(ClientesEntity clientesEntity){
        jpaClientesDAO.save(clientesEntity);
    }
}
