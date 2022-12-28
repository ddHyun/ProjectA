package org.tourGo.controller.community.review;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tourGo.common.Pagination;
import org.tourGo.models.community.review.LikedEntityRepository;
import org.tourGo.models.entity.community.review.LikedEntity;
import org.tourGo.models.entity.community.review.ReviewEntity;
import org.tourGo.service.community.review.ReviewService;

@Controller
public class ReviewListController{
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private LikedEntityRepository likedRepository;

	private String baseUrl = "community/review/";

	
	//여행후기 메인페이지
	@GetMapping("/community/review_main")
	public String index(@RequestParam(name="page", required=false) Integer page, 
						String keyword, String order, Model model){		
		//css, js, board 추가
		model.addAttribute("board", "review");
		model.addAttribute("addCss", new String[] {"community/community_common", "community/pagination"});
		model.addAttribute("addScript", new String[] {"community/review/index"});
		
		page = page == null? 1 : page;
		
		Page<ReviewEntity> results = reviewService.getAllReviewList(page, 10, order, keyword);
		//Page<엔티티>->List<엔티티>->Stream<엔티티>->Stream<커맨드>->List<커맨드>
		List<ReviewRequest> lists = results.getContent().stream().map(ReviewRequest::new).toList();
		String url = "/community/review_main";
		Pagination<ReviewEntity> pagination = new Pagination<>(results, url);		
		model.addAttribute("lists", lists);
		model.addAttribute("pagination", pagination);
		
		//추천수
		long[] totalLikes = new long[10];
		for(int i=0; i<results.getContent().size(); i++) {
			totalLikes[i] = likedRepository.countByReviewAndLiked(results.getContent().get(i), true);
		}
		model.addAttribute("totalLikes", totalLikes);		
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);		
		
		return baseUrl+ "review_main";
			
	}	
}
