package AgroApp_BackEnd.cliente.controller;

import AgroApp_BackEnd.cliente.dao.JPAClientesDAO;
import AgroApp_BackEnd.cliente.dto.entrada.ClienteEntrada;
import AgroApp_BackEnd.Repository.entity.ClientesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class ClienteController {

    @Autowired
    private JPAClientesDAO jpaClientesDAO;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastroUsuarios(@RequestBody ClienteEntrada clienteEntrada){

        Optional<ClientesEntity> clientes = jpaClientesDAO
                .findByEmailClienteAndSenhaCliente(clienteEntrada.getEmailUsuario(), clienteEntrada.getSenhaUsuario());
        if(clientes.isPresent()){
            return new ResponseEntity<>("Usuário com este email já existe!", HttpStatus.NOT_ACCEPTABLE);
        }
        ClientesEntity clientesEntity = new ClientesEntity(clienteEntrada);
        jpaClientesDAO.save(clientesEntity);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/login")
    public ResponseEntity loginUsuario(@RequestParam(value = "email") String email,
                                                   @RequestParam(value = "senha") String senha){

       Optional<ClientesEntity> clientesEntity = jpaClientesDAO.findByEmailClienteAndSenhaCliente(email, senha);

       if(clientesEntity.isEmpty()){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok().build();
    }
}
