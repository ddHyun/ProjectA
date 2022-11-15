package org.tourGo.service.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.community.review.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;
}
