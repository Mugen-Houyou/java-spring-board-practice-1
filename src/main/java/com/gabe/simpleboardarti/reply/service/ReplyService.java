package com.gabe.simpleboardarti.reply.service;

import com.gabe.simpleboardarti.post.db.PostEntity;
import com.gabe.simpleboardarti.post.db.PostRepository;
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
    private final PostRepository postRepository;

    public ReplyEntity create(
            ReplyRequest replyRequest
    ){
        var postEntity = postRepository.findById(replyRequest.getPostId()).get();
        var entity = ReplyEntity.builder()
                .post(postEntity)
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
