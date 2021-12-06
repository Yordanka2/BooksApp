package dany.fmi.master.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"comments"})
public class UserEntity implements Serializable {
		  
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username", length = 255, nullable = false, unique = true)
	private String username;
	@Column(name="password", length = 32, nullable = false)
	private String password;
	@Column(name="email", length = 255, nullable = false, unique = true)
	private String email;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<ReviewEntity> reviews;
	
	@ManyToMany
	@JoinTable(name = "account_role", 
	joinColumns=@JoinColumn(name="account_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<RoleEntity> roles;
		
	public UserEntity() {	}
	
	public UserEntity(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public UserEntity(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ReviewEntity> getDescription() {
		return reviews;
	}

	public void setComments(List<ReviewEntity> description) {
		this.reviews = description;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}	
		
}