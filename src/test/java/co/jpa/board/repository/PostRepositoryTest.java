package co.jpa.board.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.jpa.board.config.JpaConfig;
import co.jpa.board.domain.Post;
import co.jpa.board.domain.PostComment;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@SpringBootTest
public class PostRepositoryTest {
	@Autowired
    private PostRepository postrepository;
	@Autowired
	private PostCommentRepository commentrepository;
		
	@Test
	@DisplayName("select 테스트")
	public void selectTest() {
		List<Post>postlist = new ArrayList<>();
		
		postlist = postrepository.findAll();
		
		assertThat(postlist)
		.isNotNull()
		.hasSize(9);
	}
	
	@Test
	@DisplayName("insert 테스트")
	@Transactional
	public void insertTest() {
		long previousCount = postrepository.count();
		
		System.out.println("previousCount:"+previousCount);
		
		Post post =  postrepository.save(Post.of("new post","new contents","spring","tester1","tester1"));
		
		//System.out.println("result"+ post);
		
		assertThat(postrepository.count()).isEqualTo(previousCount+1);
	}
	
	@Test
	@DisplayName("update 테스트")
	public void updateTest() {
		
		Post post = postrepository.findById(1L).orElseThrow();
		String updatehashtag = "#springboot";
		post.setHashtag(updatehashtag);
		
		Post postsave = postrepository.save(post);
		
		assertThat(postsave).hasFieldOrPropertyWithValue("hashtag",updatehashtag);
	}
	
	@Test
	@DisplayName("delete 테스트")
	public void deleteTest() {
		Post post = postrepository.findById(10L).orElseThrow();
		long previousArticleCount = postrepository.count();
        long previousArticleCommentCount = commentrepository.count();
        int deletedCommentsSize = post.getArticleComments().size();
        
        postrepository.delete(post);
        
        assertThat(postrepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(commentrepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
	}
	
	@Test
	@DisplayName("댓글 insert테스트")
	public void comment_insert() {
		//글조회
		Post post = postrepository.findById(1L).orElseThrow();
		
		PostComment comment = commentrepository.save(PostComment.of("댓글 내용", post,"reply_writer","reply_updater"));
		System.out.println("댓글 내용:"+comment);
		assertThat(comment.getContents()).isEqualTo("댓글 내용");
	}
}
