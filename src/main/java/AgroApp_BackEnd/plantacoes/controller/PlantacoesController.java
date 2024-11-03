package AgroApp_BackEnd.plantacoes.controller;

import AgroApp_BackEnd.plantacoes.dto.entrada.PlantacoesProntas;
import AgroApp_BackEnd.plantacoes.dto.saida.PlantacoesCadastradas;
import AgroApp_BackEnd.plantacoes.facade.PlantacoesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantacoes")
public class PlantacoesController {

    @Autowired
    private PlantacoesFacade plantacoesFacade;

    @GetMapping("/fornecimentos-cadastrados")
    public List<PlantacoesCadastradas> retornaTodasFornecimentosCadastrados (){
        return plantacoesFacade.deveReetornarPlantacoesCadastradas();
    }

    @PatchMapping("/definir-pronto")
    public ResponseEntity<String> DefinePlantacaoComoPronto(@RequestBody List<PlantacoesProntas> plantacoesProntas){
        plantacoesFacade.deveDefinirPlantacaoComoPronto(plantacoesProntas);
        return ResponseEntity.ok().build();
    }
}
