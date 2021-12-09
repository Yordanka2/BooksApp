package dany.fmi.master.BooksApp.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dany.fmi.master.BooksApp.entities.ReviewEntity;
import dany.fmi.master.BooksApp.entities.UserEntity;
import dany.fmi.master.BooksApp.repositories.ReviewRepository;


@RestController
public class ReviewsController {


	
	ReviewRepository reviewRepository;
	
	public ReviewsController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	
	@PostMapping(path = "/review/add")
	public String addReview(
			@RequestParam(value = "genre") String genre,
			@RequestParam(value = "author") String author,
			@RequestParam(value = "review") String review,
			@RequestParam(value = "heading") String heading,

			HttpSession session			
			) {
		
	UserEntity user = (UserEntity) session.getAttribute("user");
		
		if(user != null) {
			
			ReviewEntity newReview = new ReviewEntity();
			
			newReview.setGenre(genre);
			newReview.setAuthor(author);
			newReview.setHeading(heading);
			newReview.setReview(review);
			newReview.setUser(user);
 			
			newReview = reviewRepository.saveAndFlush(newReview);			
			
			if(newReview != null) {
				return String.valueOf(newReview.getId());
			}
			
			return "Error: insert unsuccessfull!";
		}
		
		return "Error: there is no logged user!";		
	}
	
	@GetMapping(path ="/review/all")
	public List<ReviewEntity> getAllReviews(){
		return reviewRepository.findAll();		
	}
	
	
	@DeleteMapping(path = "/review/delete")
	//@Secured("ROLE_ADMIN")
	public ResponseEntity<Boolean> deleteReview(
			@RequestParam(value = "id") int id,
			HttpSession session){
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		
		if(user == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
		
		Optional<ReviewEntity> optinalReview = reviewRepository.findById(id);
		
		if(optinalReview.isPresent()) {
			ReviewEntity review = optinalReview.get();
			
			if(review.getUser().getId() == user.getId()) {
				reviewRepository.delete(review);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			}
						
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);			
		}		
	}	
}