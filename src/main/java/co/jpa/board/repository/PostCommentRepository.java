package co.jpa.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;

import co.jpa.board.domain.PostComment;
import co.jpa.board.domain.QPostComment;

@EnableJpaRepositories
@RepositoryRestResource
public interface PostCommentRepository extends 
					JpaRepository<PostComment, Long>,
					QuerydslPredicateExecutor<PostComment>,
					QuerydslBinderCustomizer<QPostComment>{

	@Override
	default void customize(QuerydslBindings bindings, QPostComment root) {

		bindings.excludeUnlistedProperties(true);
		bindings.including(root.contents, root.createdAt, root.createdBy);
        bindings.bind(root.contents).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
	}
	
}
