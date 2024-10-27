package AgroApp_BackEnd.cliente.controller;

import AgroApp_BackEnd.cliente.dto.entrada.ClienteEntrada;
import AgroApp_BackEnd.cliente.facade.ClientesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class ClienteController {

    @Autowired
    private ClientesFacade clientesFacade;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastroUsuarios(@RequestBody ClienteEntrada clienteEntrada){
        return clientesFacade.cadastraNovoCliente(clienteEntrada);
    }


    @GetMapping("/login")
    public ResponseEntity loginUsuario(@RequestParam(value = "email") String email,
                                                   @RequestParam(value = "senha") String senha){
        return clientesFacade.loginUsuario(email, senha);
    }
}
