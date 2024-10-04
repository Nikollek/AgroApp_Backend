package AgroApp_BackEnd.Repository.entity;

import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPJEntrada;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "PessoaJuridica")
@Entity(name = "PessoaJuridica")
public class FornecedoresPJEntity {

    @Id
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "uf")
    private String uf;

    @Column(name = "municipio")
    private String municipio;

    public FornecedoresPJEntity(FornecedoresPJEntrada fornecedoresPJEntrada) {
        this.cnpj = fornecedoresPJEntrada.getCnpj();
        this.razaoSocial = fornecedoresPJEntrada.getRazaoSocial();
        this.nomeFantasia = fornecedoresPJEntrada.getNomeFantasia();
        this.telefone = fornecedoresPJEntrada.getTelefone();
        this.bairro = fornecedoresPJEntrada.getBairro();
        this.cep = fornecedoresPJEntrada.getCep();
        this.uf = fornecedoresPJEntrada.getUf();
        this.municipio = fornecedoresPJEntrada.getMunicipio();
    }
}
