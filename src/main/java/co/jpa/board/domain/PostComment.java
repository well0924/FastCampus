package co.jpa.board.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.jpa.board.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "contents"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class PostComment extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter
	@ManyToOne(optional = false)
	private Post post;
	
	@Setter 
	@Column(nullable = false, length = 500)
	private String contents;
	
	protected PostComment(){
		
	}
	
	private PostComment(String contents,Post post,String createdBy,String updatedBy) {
		this.contents = contents;
		this.post = post;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
	
	public static PostComment of(String contents,Post post,String createdBy,String updatedBy) {
		return new PostComment(contents,post,createdBy,updatedBy);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComment that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
    
}
