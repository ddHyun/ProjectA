package org.tourGo.service.community.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tourGo.models.community.query.QueryEntityRepository;
import org.tourGo.models.community.review.ReviewEntityRepository;
import org.tourGo.models.community.review.UidEntityRepository;
import org.tourGo.models.entity.community.review.UidEntity;
import org.tourGo.models.notice.NoticeRepository;

/*조회수 처리*/

@Service
public class ReadHitService {

	@Autowired
	private UidEntityRepository uidRepository;
	@Autowired
	private QueryEntityRepository queryRepository;
	@Autowired
	private ReviewEntityRepository reviewRepository;
	@Autowired
	private NoticeRepository noticeRepository;
	
	public void process(String uid, String field, String boardName) {
		//중복 확인 후 없으면 엔티티 등록
		UidEntity uidEntity = uidRepository.findByFieldAndUid(field, uid).orElse(null);
		
		if(uidEntity == null) {
			uidEntity = UidEntity.builder().uid(uid).field(field).build();
			uidRepository.save(uidEntity);
			
			long boardNo = Long.parseLong(uid.split("_")[0]);
			
			//게시판 별 조회수 update
			switch(boardName) {
				case "query"	: 
					queryRepository.updateQueryRead(boardNo);
					break;
				case "review" :
					reviewRepository.updateReviewRead(boardNo);
					break;
				case "notice" :
					noticeRepository.updateViewCount(boardNo);
					break;
			}
		}		
	}
}
