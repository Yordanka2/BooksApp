package dany.fmi.master.BooksApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dany.fmi.master.BooksApp.entities.ReviewEntity;



@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
}
