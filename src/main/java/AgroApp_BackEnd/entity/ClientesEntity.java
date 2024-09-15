package AgroApp_BackEnd.entity;

import AgroApp_BackEnd.Cliente.Domain.Entrada.ClienteEntrada;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Clientes")
@Entity(name = "Clientes")
@NoArgsConstructor
@Getter
@Setter
public class ClientesEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nome")
    private String nomeCliente;

    @Column(name = "email")
    private String emailCliente;

    @Column(name = "senha")
    private String senhaCliente;

    public ClientesEntity(ClienteEntrada clienteEntrada){
        this.nomeCliente = clienteEntrada.getNomeUsuario();
        this.emailCliente = clienteEntrada.getEmailUsuario();
        this.senhaCliente = clienteEntrada.getSenhaUsuario();
    }
}
