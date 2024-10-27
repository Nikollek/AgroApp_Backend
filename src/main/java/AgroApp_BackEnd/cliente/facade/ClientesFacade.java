package AgroApp_BackEnd.cliente.facade;

import AgroApp_BackEnd.Repository.entity.ClientesEntity;
import AgroApp_BackEnd.cliente.dto.entrada.ClienteEntrada;
import AgroApp_BackEnd.cliente.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientesFacade {

    @Autowired
    private ClientesService clientesService;

    public ResponseEntity<String> cadastraNovoCliente(ClienteEntrada clienteEntrada){

        Optional<ClientesEntity> clientesEntity =  clientesService
                .buscaCliente(clienteEntrada.getEmailUsuario());

        if(clientesEntity.isPresent()){
            return new ResponseEntity<>("Usuário com este email já existe!", HttpStatus.NOT_ACCEPTABLE);
        }
        ClientesEntity clientes = new ClientesEntity(clienteEntrada);
        clientesService.salvarCliente(clientes);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> loginUsuario(String email, String senha){
        Optional<ClientesEntity> clientesEntity = clientesService.buscaCliente(email, senha);
        if (clientesEntity.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
