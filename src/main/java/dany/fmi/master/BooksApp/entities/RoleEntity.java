package dany.fmi.master.BooksApp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "roles")
public class RoleEntity implements Serializable {
    @Id
    @Column(name ="role_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
    
    @Column(name = "code", unique=true, nullable=false)
    private String code;
    
    @Column(name ="description")
    private String description;

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}