package com.synex.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.synex.pojo.Article;
import jakarta.transaction.Transactional;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Long> {

	Article save(Article article);

	Optional<Article> findById(Long articleId);
	
	@Modifying
    @Transactional
    @Query("UPDATE Article a SET a.likes = :likes WHERE a.id = :articleId")
    void updateLikes(@Param("articleId") Long articleId, @Param("likes") int likes);
	
	void deleteById(Long articleId);

}
