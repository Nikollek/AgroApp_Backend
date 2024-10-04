package AgroApp_BackEnd.Repository.entity;

import AgroApp_BackEnd.fornecedores.dto.entrada.FornecedoresPFEntrada;
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
@Table(name = "PessoaFisica")
@Entity(name = "PessoaFisica")

public class FornecedoresPFEntity {

    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private String idade;

    public FornecedoresPFEntity(FornecedoresPFEntrada fornecedoresPFEntrada) {
        this.cpf = fornecedoresPFEntrada.getCpf();
        this.nome = fornecedoresPFEntrada.getNome();
        this.idade = fornecedoresPFEntrada.getIdade();
    }
}
