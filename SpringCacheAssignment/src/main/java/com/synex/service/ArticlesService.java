package com.synex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.synex.pojo.Article;
import com.synex.repository.ArticlesRepository;

@Service
public class ArticlesService {

	@Autowired
	private ArticlesRepository articleRepository;

	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	@Cacheable(value = "article", key = "#articleId")
	public Article getArticle(Long articleId) {
		System.out.println("Fetching from DB for articleId: " + articleId);
		return articleRepository.findById(articleId).orElse(null);
	}

	@CachePut(value = "article", key = "#articleId")
	public Article updateLikes(Long articleId, int likes) {
		System.out.println("Updating in DB and cache");
		articleRepository.updateLikes(articleId, likes);
		return articleRepository.findById(articleId).orElse(null);
	}

	@CacheEvict(value = "article", key = "#articleId")
	public void removeArticle(Long articleId) {
		articleRepository.deleteById(articleId);
	}
}
