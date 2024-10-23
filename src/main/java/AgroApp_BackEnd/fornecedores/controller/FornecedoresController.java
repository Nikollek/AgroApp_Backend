package AgroApp_BackEnd.fornecedores.controller;

import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPFEntrada;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPJEntrada;
import AgroApp_BackEnd.fornecedores.facade.FornecedoreFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedoresController {

    @Autowired
    private FornecedoreFacade fornecedoresFacade;

    @PostMapping("/pessoa-juridica")
    public ResponseEntity<String> cadastroFornecedoresPJ (@RequestBody FornecedoresPJEntrada fornecedoresPJEntrada){
        fornecedoresFacade.deveSalvarNovoFornecedeorEPlantio(fornecedoresPJEntrada);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<String> cadastroFornecedoresPF (@RequestBody FornecedoresPFEntrada fornecedoresPFEntrada){
        fornecedoresFacade.deveSalvarNovoFornecedeorEPlantio(fornecedoresPFEntrada);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fornecimentos-disponiveis")
    public List<String> retornaTiposDeAlimento (){
        return fornecedoresFacade.deveRetornarFornecimentos();
    }


}
