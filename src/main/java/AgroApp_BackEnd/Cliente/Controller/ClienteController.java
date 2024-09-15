package AgroApp_BackEnd.Cliente.Controller;

import AgroApp_BackEnd.Repository.ClienteRepository;
import AgroApp_BackEnd.Cliente.Domain.Entrada.ClienteEntrada;
import AgroApp_BackEnd.entity.ClientesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastroUsuario(@RequestBody ClienteEntrada clienteEntrada){

        Optional<ClientesEntity> clientes = repository
                .findByEmailClienteAndSenhaCliente(clienteEntrada.getEmailUsuario(), clienteEntrada.getSenhaUsuario());
        if(clientes.isPresent()){
            return new ResponseEntity<>("Usuário com este email já existe!", HttpStatus.NOT_ACCEPTABLE);
        }
        ClientesEntity clientesEntity = new ClientesEntity(clienteEntrada);
        repository.save(clientesEntity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping("/login")
    public ResponseEntity loginUsuario(@RequestParam(value = "email") String email,
                                                   @RequestParam(value = "senha") String senha){

       Optional<ClientesEntity> clientesEntity = repository.findByEmailClienteAndSenhaCliente(email, senha);

       if(clientesEntity.isEmpty()){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok().build();
    }
}
