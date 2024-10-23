package AgroApp_BackEnd.Repository.entity;

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
@Table(name = "Fornecedores")
@Entity(name = "Fornecedores")
public class FornecedoresEntity {

    @Id
    @Column(name = "id_fornecedor")
    private Integer idFornecedor;

    @Column(name = "tipo_pessoa")
    private String tipoPessoa;

    @Column(name = "cnpj")
    private String idPessoaJuridica;

    @Column(name = "id_pessoa_fisica")
    private String idPessoaFisica;

}
