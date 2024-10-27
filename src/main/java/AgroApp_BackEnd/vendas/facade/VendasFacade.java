package AgroApp_BackEnd.vendas.facade;

import AgroApp_BackEnd.Repository.entity.*;
import AgroApp_BackEnd.cliente.service.ClientesService;
import AgroApp_BackEnd.fornecedores.service.FornecedorService;
import AgroApp_BackEnd.vendas.dto.entrada.AlimentosDisponiveis;
import AgroApp_BackEnd.vendas.dto.entrada.IngressoSolidario;
import AgroApp_BackEnd.vendas.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VendasFacade {

    @Autowired
    private VendasService vendasService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private FornecedorService fornecedorService;

    public ResponseEntity<String> cadastraVendaIngresso(IngressoSolidario ingressoSolidario, String email, String senha) {
        Optional<ClientesEntity> clientesEntity = clientesService.buscaCliente(email, senha);

        Long idCliente = clientesEntity
                .map(ClientesEntity::getId)
                .orElse(null);

        if(idCliente == null){
            return ResponseEntity.badRequest().build();
        }

        VendaVisitaEntity vendaVisitaEntity = new VendaVisitaEntity(ingressoSolidario, idCliente);
        vendasService.salvarCadastroVendaVisita(vendaVisitaEntity);
        return ResponseEntity.ok().build();
    }

    public List<AlimentosDisponiveis> deveRetornarAlimentosProntos() {
        //retorna registro da plantacao onde os alimento estejam prontos
        List<PlantacaoEntity> plantacaoEntities = vendasService.deveRetornanAlimentoPronto(Boolean.TRUE);
        //busca idPlantios do registro das plantacoes
        List<Long> idsPlantios = plantacaoEntities.stream().map(PlantacaoEntity::getIdPlantios).collect(Collectors.toList());
        //retorna entidades dos plantios com os Ids dos registro da plantacao
        List<PlantiosEntity> plantiosEntities = fornecedorService.retornaPlantios(idsPlantios);

        //retorna alimento prontos
        return plantacaoEntities.stream()
                .map(plantacaoEntity -> {
                    AlimentosDisponiveis alimentosDisponiveis = new AlimentosDisponiveis();

                    //Filtrar os plantiosEntities para encontrar o nome correspondente
                    plantiosEntities.stream()
                            .filter(plantios -> plantios.getIdPlantios().equals(plantacaoEntity.getIdPlantios())) //compara os IDs
                            .findFirst() //pega o primeiro PlantiosEntityEncontrado com o Id
                            .ifPresent(plantiosEntity -> {
                                //se encontrar, seta o valor e o nome no alimentos disponiveis
                                alimentosDisponiveis.setIdPlantacao(plantacaoEntity.getId());
                                alimentosDisponiveis.setValorAlimento(plantacaoEntity.getValorFinal());
                                alimentosDisponiveis.setNomeAlimento(plantiosEntity.getDescricao().trim());
                            });
                    return alimentosDisponiveis;
                }).collect(Collectors.toList());
    }

    public ResponseEntity<String> cadastraVendaAlimentos(List<Long> idPlantacoes, String email) {
        //busca registro de alimentos escolhidos
        List<PlantacaoEntity> plantacaoEntities = vendasService.deveRetornanAlimentoPronto(idPlantacoes);
        //busca cliente que fez a compra
        Optional<ClientesEntity> clientesEntity = clientesService.buscaCliente(email);

        //Somar todos os produtos escolhidos
        Double totalVenda = plantacaoEntities.stream()
                .mapToDouble(PlantacaoEntity::getValorFinal)
                .sum();
        //busca idCliente do comprador
        Long idCliente = clientesEntity
                .map(ClientesEntity::getId)
                .orElse(null);

        //mapear entidade da venda do alimento
        VendaAlimentoEntity vendaAlimentoEntity = new VendaAlimentoEntity(totalVenda, idCliente);
        //salvar venda do alimento na base
        VendaAlimentoEntity alimento = vendasService.salvarCadastroVendaAlimento(vendaAlimentoEntity);

        plantacaoEntities.forEach(plantacaoEntity -> plantacaoEntity.setIdVenda(alimento.getIdVenda()));
        fornecedorService.salvarPlantacao(plantacaoEntities);

        return ResponseEntity.ok().build();
    }
}
