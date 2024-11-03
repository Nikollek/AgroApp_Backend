package AgroApp_BackEnd.fornecedores.facade;

import AgroApp_BackEnd.Repository.entity.*;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPFEntrada;
import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPJEntrada;
import AgroApp_BackEnd.fornecedores.dto.saida.FornecedoresCadastrados;
import AgroApp_BackEnd.fornecedores.service.FornecedorService;
import AgroApp_BackEnd.integracao.openIA.OpenAIService;
import AgroApp_BackEnd.integracao.openIA.dto.saida.InformacaoAlimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FornecedoreFacade {

    @Autowired
    private FornecedorService fornecedorService;


    public void deveSalvarNovoFornecedeorEPlantio(FornecedoresPFEntrada fornecedoresPFEntrada) {

        //busca se o fornecedor PF ja existe no banco de dados
        Optional<FornecedoresPFEntity> fornecedoresPFEntity = fornecedorService.retornaFornecedorPF(fornecedoresPFEntrada.getTelefone());

        //caso exista é enviado ao controller a mensagem
        if(fornecedoresPFEntity.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Fornecedor já existe.");
        }

        //os valores enviados são mapeados para a entidade de fornecedor PF
        FornecedoresPFEntity fornecedoresPF = new FornecedoresPFEntity(fornecedoresPFEntrada);

        //salvar novo fornecedor PF
        fornecedorService.salvarFornecedorPF(fornecedoresPF);

        //busca tipo fornecimento
        String tipoFornecimento = fornecedoresPFEntrada.getFornecimento().getTipoFornecimento().name();
        Integer codigoFornecimento = fornecedoresPFEntrada.getFornecimento().getTipoFornecimento().getCodigo();

        //salvar plantacao cadastrada
        salvarNovaPlantacao(fornecedoresPF,null,
                tipoFornecimento,
                Long.valueOf(codigoFornecimento),
                fornecedoresPFEntrada.getFornecimento().getQuantidade());
    }


    public void deveSalvarNovoFornecedeorEPlantio(FornecedoresPJEntrada fornecedoresPJEntrada) {
        Optional<FornecedoresPJEntity> fornecedoresPJEntity = fornecedorService.retornaFornecedorPJ(fornecedoresPJEntrada.getCnpj());

        if(fornecedoresPJEntity.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Fornecedor já existe.");
        }

        //os valores enviados são mapeados para a entidade de fornecedor PJ
        FornecedoresPJEntity fornecedoresPJ = new FornecedoresPJEntity(fornecedoresPJEntrada);

        //salva novo fornecedor PJ
        fornecedorService.salvarFornecedorPJ(fornecedoresPJ);

        //busca tipo fornecimento
        String tipoFornecimento = fornecedoresPJEntrada.getFornecimento().getTipoFornecimento().name();
        Integer codigoFornecimento = fornecedoresPJEntrada.getFornecimento().getTipoFornecimento().getCodigo();

        //salvar plantacao cadastrada
        salvarNovaPlantacao(null, fornecedoresPJ,
                tipoFornecimento,
                Long.valueOf(codigoFornecimento),
                fornecedoresPJEntrada.getFornecimento().getQuantidade());
    }

    private static InformacaoAlimento getInformacaoAlimento(String tipoFornecimento) {

        InformacaoAlimento informacaoAlimento = null;

        try {
            //busca Script simulacao IA
            String jsonData = new String(Files.readAllBytes(Paths.get("src/main/resources/scriptIA.json")));
            //Mapeia json para achar melhor clima, regiao e tempo para aquele alimento
            informacaoAlimento = OpenAIService.buscarInformacoesPorNomeAlimento(jsonData, tipoFornecimento);
        } catch (IOException e) {
            e.getMessage();
        }

        //retorna as informações do alimento
        return informacaoAlimento;
    }

    private void salvarNovaPlantacao(FornecedoresPFEntity fornecedoresPF,
                                     FornecedoresPJEntity fornecedoresPJ,
                                     String tipoFornecimento,
                                     Long codigoFornecimento,
                                     Long quantidade) {
        //Simulacao de IA para buscar melho regiao, tempo e clima para a plantação
        InformacaoAlimento informacaoAlimento = getInformacaoAlimento(tipoFornecimento);

        //definir id fornecedor sendo pf ou pj
        Integer idFornecedor;
        FornecedoresEntity fornecedoresEntity;
        if(fornecedoresPF != null) {
            fornecedoresEntity = fornecedorService.retornaFornecedor(fornecedoresPF);
        }else{
            fornecedoresEntity = fornecedorService.retornaFornecedor(fornecedoresPJ);
        }
        idFornecedor = fornecedoresEntity.getIdFornecedor();

        //mapear entidade plantação
        PlantacaoEntity plantacaoEntity = new PlantacaoEntity(informacaoAlimento, codigoFornecimento, idFornecedor);
        //salvar novo registro de plantacao
        fornecedorService.salvarPlantacao(plantacaoEntity);

        //se a quantidade definida pelo fornecedor for maior que 1, então é feito uma duplicata da mesmo tipo de plantação
        if(quantidade > 1){
            for(int i = 1; i < quantidade; i++){
                PlantacaoEntity duplicataPlantacaoEntity = new PlantacaoEntity(informacaoAlimento, codigoFornecimento, idFornecedor);
                fornecedorService.salvarPlantacao(duplicataPlantacaoEntity);
            }
        }

    }

    public List<String> deveRetornarFornecimentos() {
        //recupera todos os tipos de plantacao do banco
        List<PlantiosEntity> plantios = fornecedorService.retornaPlantios(null);

        //obtem do banco de dados apena os nomes
        return plantios.stream()
                .map(p -> p.getDescricao().trim())
                .collect(Collectors.toList());
    }

    public List<FornecedoresCadastrados> deveRetornarTodosFornecedoresCadastrados() {
        // Busca todos os fornecedores
        List<FornecedoresEntity> fornecedoresEntities = fornecedorService.retornaFornecedores();

        // Processa cada FornecedoresEntity
        return fornecedoresEntities.stream()
                // Mapeia cada FornecedoresEntity para FornecedoresCadastrados com base no tipo de pessoa
                .map(fornecedoresEntity -> {
                    // Se o tipo de pessoa for "J" (jurídica)
                    if ("J".equals(fornecedoresEntity.getTipoPessoa())) {
                        // Obtém os detalhes do fornecedor jurídico a partir do serviço
                        Optional<FornecedoresPJEntity> pjEntity = fornecedorService
                                .retornaFornecedorPJ(fornecedoresEntity.getIdPessoaJuridica());

                        // Mapeia os detalhes do fornecedor jurídico para FornecedoresCadastrados, se presente
                        return pjEntity.map(juridico -> new FornecedoresCadastrados(
                                fornecedoresEntity.getTipoPessoa(),
                                juridico.getNomeFantasia().trim(),
                                juridico.getTelefone().trim())
                        ).orElse(null);
                    }
                    // Se o tipo de pessoa for "F" (física)
                    else if ("F".equals(fornecedoresEntity.getTipoPessoa())) {
                        // Obtém os detalhes do fornecedor físico a partir do serviço
                        Optional<FornecedoresPFEntity> pfEntity = fornecedorService
                                .retornaFornecedorPF(fornecedoresEntity.getIdPessoaFisica());

                        // Mapeia os detalhes do fornecedor físico para FornecedoresCadastrados, se presente
                        return pfEntity.map(fisico -> new FornecedoresCadastrados(
                                fornecedoresEntity.getTipoPessoa(),
                                fisico.getNome().trim(),
                                fisico.getTelefone().trim())
                        ).orElse(null);
                    }
                    // Retorna null se o tipo de pessoa não for "J" nem "F"
                    return null;
                })
                // Remove os valores nulos do stream
                .filter(Objects::nonNull)
                // Coleta os resultados finais em uma lista
                .collect(Collectors.toList());
    }

}
