package org.tourGo.service.destination;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tourGo.models.destination.entity.DestinationMain;

public interface DestinationMainRepository extends JpaRepository<DestinationMain, Long>{
	
	// Dao 메소드가 Repository로 생각하면 되고
	List<DestinationMain> findAllByUser(Long userId, Sort sort);

}
