package com.gabe.simpleboardarti.post.service;

import com.gabe.simpleboardarti.board.db.BoardRepository;
import com.gabe.simpleboardarti.post.db.PostEntity;
import com.gabe.simpleboardarti.post.db.PostRepository;
import com.gabe.simpleboardarti.post.model.PostRequest;
import com.gabe.simpleboardarti.post.model.PostViewRequest;
import com.gabe.simpleboardarti.reply.db.ReplyEntity;
import com.gabe.simpleboardarti.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final ReplyService replyService;

    public PostEntity create(
            PostRequest postRequest

    ){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); // 그냥 .get()을 박으면 안됨 ==> TODO: 해당 부분 수정 필요
        var result = PostEntity.builder()
                .board(boardEntity) // ==> 1로 임시 고정!
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .status("REGISTERED")
                .email(postRequest.getEmail())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(result);
    }

    /*
    로직 요구사항
    1. 게시글 존재 여부
    2. 암호 일치 여부
     */
    public PostEntity view(@Valid PostViewRequest postViewRequest) {
        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map( it -> {
                    // entity 존재할 때?
                    if(it.getPassword().equals(postViewRequest.getPassword())){
                        log.info("게시글 열람하였음.{}",it.getId());
//                        it.setListReplies(replyService.findAllByPostId(it.getId()));
                        return it;
                    }else{
                        var formattedMessage = "암호 틀림! 실제 암호: %s / 입력 암호: %s";
                        throw new RuntimeException(String.format(formattedMessage, it.getPassword(), postViewRequest.getPassword() ));
                    }
                }).orElseThrow(() ->{
                    var formattedMessage = "해당 게시글이 없음! %s";
                    return new RuntimeException(String.format(formattedMessage,postViewRequest.getPostId()));
                });
    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }

    public PostEntity delete(@Valid PostViewRequest postViewRequest) {
        return postRepository.findById(postViewRequest.getPostId())
                .map( it -> {
                    // entity 존재할 때?
                    if(it.getPassword().equals(postViewRequest.getPassword())){
                        log.info("게시글 삭제하였음.{}",it.getId());
                        it.setStatus("UNREGISTERED");
                        postRepository.save(it);
                        return it;
                    }else{
                        var formattedMessage = "암호 틀림! 실제 암호: %s / 입력 암호: %s";
                        throw new RuntimeException(String.format(formattedMessage, it.getPassword(), postViewRequest.getPassword() ));
                    }
                }).orElseThrow(() ->{
                    var formattedMessage = "해당 게시글이 없음! %s";
                    return new RuntimeException(String.format(formattedMessage,postViewRequest.getPostId()));
                });

    }

}





















