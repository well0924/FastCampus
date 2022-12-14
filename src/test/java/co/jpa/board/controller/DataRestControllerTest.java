package co.jpa.board.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@DisplayName("컨트롤러 테스트")
@SpringBootTest
public class DataRestControllerTest {
	
	private final MockMvc mvc;
	
	public DataRestControllerTest(@Autowired MockMvc mvc) {
		this.mvc = mvc;
	}
	
	@Test
	@DisplayName("[api] 게시글 전체 조회")
	public void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
		mvc.perform(get("/api/posts"))
        .andExpect(status().isOk())
        .andDo(print());
	}
	
	@DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
	
	@DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/posts/1/articleComments"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/postComments"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/postComments/1"))
                .andExpect(status().isOk()).andDo(print());
    }
}
