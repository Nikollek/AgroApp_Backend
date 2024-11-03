package AgroApp_BackEnd.vendas.facade;

import AgroApp_BackEnd.Repository.entity.*;
import AgroApp_BackEnd.cliente.service.ClientesService;
import AgroApp_BackEnd.enuns.Fornecimentos;
import AgroApp_BackEnd.fornecedores.service.FornecedorService;
import AgroApp_BackEnd.plantacoes.service.PlantacoesService;
import AgroApp_BackEnd.vendas.dto.saida.AlimentosDisponiveis;
import AgroApp_BackEnd.vendas.dto.entrada.IngressoSolidario;
import AgroApp_BackEnd.vendas.dto.saida.Relatorios;
import AgroApp_BackEnd.vendas.dto.saida.VendasAlimentosEfetivadas;
import AgroApp_BackEnd.vendas.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class VendasFacade {

    @Autowired
    private VendasService vendasService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private PlantacoesService plantacoesService;

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
        List<PlantacaoEntity> plantacaoEntities = vendasService.deveRetornarAlimentoPronto(Boolean.TRUE);
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
        List<PlantacaoEntity> plantacaoEntities = vendasService.deveRetornarAlimentoPronto(idPlantacoes);
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

    public List<VendasAlimentosEfetivadas> deveRetornarTodosAlimentosVendidos() {
        //busca todas as plantacoes vendidas
        List<PlantacaoEntity> plantacaoEntityList = vendasService.deveRetornarAlimentosProntos();

        //cria um mapa onde a chave é o nome do alimento e o valor é uma instancia da classe VendasAlimentosEfetivadas
        Map<String, VendasAlimentosEfetivadas> vendasMap = new HashMap<>();

        //processa cada PlantacaoEntity
        plantacaoEntityList.stream()
                //filtra para incluir apenas as plantações que têm um idVenda não nulo
                .filter(plantacaoEntity -> plantacaoEntity.getIdVenda() != null)
                // para cada plantacaoEntity filtrada, realiza as seguintes operações:
                .forEach(plantacaoEntity -> {
                    //obtém o nome do alimento a partir do código da plantação usando o enum Fornecimentos
                    String nomeAlimento = Fornecimentos.fromCodigo(plantacaoEntity.getIdPlantios()).name();
                    //// Obtém a instância de VendasAlimentosEfetivadas correspondente ou cria uma nova se não existir
                    VendasAlimentosEfetivadas vendas = vendasMap.getOrDefault(nomeAlimento, new VendasAlimentosEfetivadas(nomeAlimento, 0l, 0.0));

                    // Atualiza a quantidade vendida e o total vendido
                    vendas.setQuantidadeVendida(vendas.getQuantidadeVendida() + 1);
                    vendas.setTotalVendido(vendas.getTotalVendido() + plantacaoEntity.getValorFinal());

                    // Coloca a instância atualizada de VendasAlimentosEfetivadas de volta no mapa
                    vendasMap.put(nomeAlimento, vendas);
                });
        // Converte o mapa de valores em uma lista e a retorna
        return new ArrayList<>(vendasMap.values());
    }

    public Relatorios deveRetornarDadosRelatorio() {

        //busca todas as visitas agendadas
        List<VendaVisitaEntity> vendaVisitaEntityList = vendasService.deveRetornarVendasVisitas();

        //busca todas as vendas de alimentos
        List<VendaAlimentoEntity> vendaAlimentoEntityList = vendasService.deveRetornarVendasAlimentos();

        //busca todos os plantacoes cadastradas
        List<PlantacaoEntity> plantacaoEntityList = plantacoesService.deveRetornarPlantacoes();

        //soma o total de todas as vendas de alimentos
        Double ganhoTotal = vendaAlimentoEntityList.stream().mapToDouble(VendaAlimentoEntity::getTotal).sum();

        Map<String, Double> top5Alimentos = getTop5Alimento(plantacaoEntityList);
        Map<String, Double> top5AlimentosIngressos = getTop5Visita(vendaVisitaEntityList);

        Relatorios relatorios = new Relatorios();
        relatorios.setTotalIngressos((long) vendaVisitaEntityList.size());
        relatorios.setTotalVendaAlimentos((long) vendaAlimentoEntityList.size());
        relatorios.setGanhoTotal(ganhoTotal);
        relatorios.setMapTopCincoAlimentosFornecidos(top5Alimentos);
        relatorios.setMapTopCincoAlimentosIngresso(top5AlimentosIngressos);
        return relatorios;
        
    }

    private static Map<String, Double> getTop5Alimento(List<PlantacaoEntity> plantacaoEntityList) {
        // calcula o top 5 alimentos mais plantados
        Map<String, Long> alimentosCount = plantacaoEntityList.stream()
                //Agrupa por tipo de alimento e conta quantos elementos estão nesse grupo
                        .collect(Collectors.groupingBy(
                                plantacoes -> Fornecimentos.fromCodigo(plantacoes.getIdPlantios()).name(),
                                Collectors.counting()
                        ));

        //Soma o total de todos os alimentos plantados
        Long totalAlimentos = alimentosCount.values().stream().mapToLong(Long::longValue).sum();

        //calcula a porcetagem de cada alimento em relação ao total
        return alimentosCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .limit(5)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> (entry.getValue() * 100.0)/ totalAlimentos,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        ));
    }

    private static Map<String, Double> getTop5Visita(List<VendaVisitaEntity> vendaVisitaEntityList){
        //obtem o top 5 alimentos mais fornecidos para o ingresso
        Map<String, Long> alimentosCount = vendaVisitaEntityList.stream()
                //agrupa por tipo de alimentos e quantos ingressos estão nesse grupo
                .collect(Collectors.groupingBy(
                        vendaVisita -> vendaVisita.getAlimentoIngresso().trim(),
                        Collectors.counting()
                ));

        //soma total de todos os alimentos fornecidos para o ingresso
        Long totalAlimentos = alimentosCount.values().stream().mapToLong(Long::longValue).sum();

        //calcula a porcentagem de cada alimento em relação ao total
        return alimentosCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() * 100.0)/ totalAlimentos,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
