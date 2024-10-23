package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantacaoRepository extends JpaRepository<PlantacaoEntity, Long> {
}
