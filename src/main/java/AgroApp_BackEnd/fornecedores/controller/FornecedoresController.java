package AgroApp_BackEnd.fornecedores.controller;

import AgroApp_BackEnd.Repository.entity.FornecedoresPFEntity;
import AgroApp_BackEnd.Repository.entity.FornecedoresPJEntity;
import AgroApp_BackEnd.fornecedores.dao.JPAFornecedoresDAO;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPFEntrada;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPJEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedoresController {

    @Autowired
    private JPAFornecedoresDAO jpaFornecedoresDAO;

    @PostMapping("/pessoa-juridica")
    public ResponseEntity<String> cadastroFornecedoresPJ (@RequestBody FornecedoresPJEntrada fornecedoresPJEntrada){

        Optional<FornecedoresPJEntity> fornecedoresPJEntity = jpaFornecedoresDAO
                .findByCnpj(fornecedoresPJEntrada.getCnpj());

        if(fornecedoresPJEntity.isPresent()){
            return new ResponseEntity<>("Fornecedor ja existe!", HttpStatus.NOT_ACCEPTABLE);
        }

        FornecedoresPJEntity fornecedoresPJ = new FornecedoresPJEntity(fornecedoresPJEntrada);
        jpaFornecedoresDAO.save(fornecedoresPJ);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<String> cadastroFornecedoresPF (@RequestBody FornecedoresPFEntrada fornecedoresPFEntrada){

        Optional<FornecedoresPFEntity> fornecedoresPFEntity = jpaFornecedoresDAO
                .findByCpf(fornecedoresPFEntrada.getCpf());

        if(fornecedoresPFEntity.isPresent()){
            return new ResponseEntity<>("Fornecedor ja existe!", HttpStatus.NOT_ACCEPTABLE);
        }

        FornecedoresPFEntity fornecedoresPF = new FornecedoresPFEntity(fornecedoresPFEntrada);
        jpaFornecedoresDAO.save(fornecedoresPF);
        return ResponseEntity.ok().build();
    }

}
