package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.synex.pojo.Article;
import com.synex.service.ArticlesService;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticlesService articleService;

	@PostMapping("/save")
	public Article saveArticle(@RequestBody Article article) {
		return articleService.saveArticle(article);
	}

	@GetMapping("/{articleId}")
	public Article getArticle(@PathVariable Long articleId) {
		Article article = articleService.getArticle(articleId);
		if(article != null) {
			return article;
		}
		return new Article(null, "No Id Found", 0);
	}


	@PutMapping("/update")
	public String updateLikes(@RequestParam Long articleId, @RequestParam int likes) {
		if(articleService.updateLikes(articleId, likes) != null) {
			return "Updated";
		}else {
			return "No Found the Id";
		}
		
	}

	@DeleteMapping("/{articleId}")
	public String deleteArticle(@PathVariable String articleId) {
		articleService.removeArticle(Long.parseLong(articleId));
		return "Deleted";
	}

}
