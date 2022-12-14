package co.jpa.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;

import co.jpa.board.domain.Post;
import co.jpa.board.domain.QPost;

@EnableJpaRepositories
@RepositoryRestResource
public interface PostRepository extends 
				JpaRepository<Post, Long>,
				QuerydslPredicateExecutor<Post>,
				QuerydslBinderCustomizer<QPost>{

	@Override
	default void customize(QuerydslBindings bindings, QPost root) {
		
		bindings.excludeUnlistedProperties(true);
		bindings.including(root.title, root.contents, root.hashtag, root.createdAt, root.createdBy);
		bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.contents).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
	}
}
