package dany.fmi.master.controllers;

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

import dany.fmi.master.entities.ReviewEntity;
import dany.fmi.master.entities.UserEntity;
import dany.fmi.master.repositories.ReviewRepository;

@RestController
public class CommentController {

	
	ReviewRepository reviewRepo;
	
	public CommentController(ReviewRepository reviewRepo) {
		this.reviewRepo = reviewRepo;
	}
	
	
	@PostMapping(path = "/review/add")
	public String addComment(
			@RequestParam(value = "genre") String genre,
			@RequestParam(value = "author") String author,
			@RequestParam(value = "review") String review,

			HttpSession session			
			) {
		
	UserEntity user = (UserEntity) session.getAttribute("user");
		
		if(user != null) {
			
			ReviewEntity newComment = new ReviewEntity();
			
			newComment.setGenre(genre);
			newComment.setAuthor(author);
		    newComment.setReview(review);
			newComment.setUser(user);
			
			newComment = reviewRepo.saveAndFlush(newComment);			
			
			if(newComment != null) {
				return String.valueOf(newComment.getId());
			}
			
			return "Error: insert unsuccessfull!";
		}
		
		return "Error: there is no logged user!";		
	}
	
	@GetMapping(path ="/comment/all")
	public List<ReviewEntity> getAllComments(){
		return reviewRepo.findAll();		
	}
	
	
	@DeleteMapping(path = "/comment/delete")
	//@Secured("ROLE_ADMIN")
	public ResponseEntity<Boolean> deleteComment(
			@RequestParam(value = "id") int id,
			HttpSession session){
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		
		if(user == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
		
		Optional<ReviewEntity> optinalComment = reviewRepo.findById(id);
		
		if(optinalComment.isPresent()) {
			ReviewEntity comment = optinalComment.get();
			
			if(comment.getUser().getId() == user.getId()) {
				reviewRepo.delete(comment);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			}
						
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);			
		}		
	}	
}