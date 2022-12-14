package co.jpa.board.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PostDto(
		Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) implements Serializable{
	
}
