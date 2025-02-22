package com.gabe.simpleboardarti.reply.service;

import com.gabe.simpleboardarti.post.db.PostEntity;
import com.gabe.simpleboardarti.reply.db.ReplyEntity;
import com.gabe.simpleboardarti.reply.db.ReplyRepository;
import com.gabe.simpleboardarti.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyEntity create(
            ReplyRequest replyRequest
    ){
        var entity = ReplyEntity.builder()
                .postId(replyRequest.getPostId())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .status("REGISTERED")
                .createdAt(LocalDateTime.now())
                .build();
        return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }

}
