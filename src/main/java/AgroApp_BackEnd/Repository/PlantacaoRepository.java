package AgroApp_BackEnd.Repository;

import AgroApp_BackEnd.Repository.entity.PlantacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantacaoRepository extends JpaRepository<PlantacaoEntity, Long> {
    List<PlantacaoEntity> findAllByFinalizadoAndIdVenda(Boolean finalizado, Long idVenda);

    List<PlantacaoEntity> findAllByIdVendaIsNotNull();
}
