package AgroApp_BackEnd.vendas.controller;

import AgroApp_BackEnd.vendas.dto.saida.AlimentosDisponiveis;
import AgroApp_BackEnd.vendas.dto.entrada.IngressoSolidario;
import AgroApp_BackEnd.vendas.dto.saida.Relatorios;
import AgroApp_BackEnd.vendas.dto.saida.VendasAlimentosEfetivadas;
import AgroApp_BackEnd.vendas.facade.VendasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasFacade vendasFacade;

    @PostMapping("/ingresso-solidario")
    public ResponseEntity<String> cadastroVendaIngressoSolidario(@RequestBody IngressoSolidario ingressoSolidario,
                                                                 @RequestParam(value = "email") String email,
                                                                 @RequestParam(value = "senha") String senha){
        return vendasFacade.cadastraVendaIngresso(ingressoSolidario, email, senha);
    }

    @GetMapping("/alimentos-prontos")
    public List<AlimentosDisponiveis> deveRetornarAlimentosDisponiveis (){
        return vendasFacade.deveRetornarAlimentosProntos();
    }

    @PostMapping("/alimentos")
    public ResponseEntity<String> cadastroVendaAlimentos(@RequestParam(value = "idPlantacao") List<Long> idPlantacoes,
                                                         @RequestParam(value = "email") final String email){
        return vendasFacade.cadastraVendaAlimentos(idPlantacoes, email);
    }

    @GetMapping("/efetivadas")
    public List<VendasAlimentosEfetivadas> retornaVendasAlimentosEfetivadas (){
        return vendasFacade.deveRetornarTodosAlimentosVendidos();
    }

    @GetMapping("/relatorios")
    public Relatorios deveRetornaDadosParaRelatorio (){
        return vendasFacade.deveRetornarDadosRelatorio();
    }

}
