package co.jpa.board.domain.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(nullable = false,updatable = false)
	protected LocalDateTime createdAt;
	
	@CreatedBy
	@Column(nullable = false,updatable = false,length=100)
	protected String createdBy;
	
	@LastModifiedDate
	protected LocalDateTime updatedAt;
	
	@LastModifiedBy
	@Column(nullable = false,length=100)
	protected String updatedBy;
}
