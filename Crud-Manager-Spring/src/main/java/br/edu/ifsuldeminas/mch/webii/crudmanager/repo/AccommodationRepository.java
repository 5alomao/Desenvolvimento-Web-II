package br.edu.ifsuldeminas.mch.webii.crudmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

	@Query("SELECT COUNT(a) > 0 FROM Accommodation a WHERE a.travelSite.id = :travelSiteId")
	boolean existsByTravelSiteId(@Param("travelSiteId") Integer travelSiteId);
}
