package co.jpa.board.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
		@Index(columnList = "title"),
		@Index(columnList = "hashtag"),
		@Index(columnList = "createdAt"),
		@Index(columnList = "createdBy")
})
public class Post extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Exclude
	@OrderBy("id")
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private final Set<PostComment> articleComments = new LinkedHashSet<>();
	
	@Setter
	@Column(nullable = false,length = 255)
	private String title;
	
	@Setter
	@Column(nullable = false,length=10000)
	private String contents;
	
	@Setter
	private String hashtag;
	
	protected Post() {}

    private Post (String title, String content,String hashtag,String createdBy,String updatedBy) {
        this.title = title;
        this.contents = content;
        this.hashtag = hashtag;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
    
    public static Post of( String title, String contents,String hashtag,String createdBy,String updatedBy) {
    	return new Post(title,contents,hashtag,createdBy,updatedBy);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
