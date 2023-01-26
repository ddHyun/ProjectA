package org.tourGo.service.community.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tourGo.models.community.review.ReplyEntityRepository;
import org.tourGo.models.entity.community.review.ReplyEntity;

@Service
public class ReplyDeleteService {
	
	@Autowired
	private ReplyEntityRepository replyRepository;
	
	// 댓글 삭제처리
	@Transactional
	public void process(ReplyEntity replyEntity){
		if(replyEntity.getDepth()==1) {//대댓글인 경우 해당 대댓글만 삭제
			replyEntity.setDeleteYn("Y");
			replyRepository.save(replyEntity);
		}else {//모댓글인 경우 하위 대댓글 같이 삭제
			String listOrder = replyEntity.getListOrder();
			List<ReplyEntity> replies = replyRepository.findByListOrder(listOrder);
			for(ReplyEntity reply : replies) {
				reply.setDeleteYn("Y");
			}
			replyRepository.saveAll(replies);
		}
	}
		
}
