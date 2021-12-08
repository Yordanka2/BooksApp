package dany.fmi.master.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dany.fmi.master.entities.ReviewEntity;



@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

}
