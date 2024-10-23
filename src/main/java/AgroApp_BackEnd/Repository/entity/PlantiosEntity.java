package AgroApp_BackEnd.Repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "Plantios")
@Entity(name = "Plantios")
public class PlantiosEntity {

    @Id
    @Column(name = "id_plantios")
    private Long idPlantios;

    @Column(name = "descricao")
    private String descricao;


}
