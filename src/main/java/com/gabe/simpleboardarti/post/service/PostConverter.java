package com.gabe.simpleboardarti.post.service;

import com.gabe.simpleboardarti.post.model.PostDto;
import com.gabe.simpleboardarti.post.db.PostEntity;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {
    public PostDto toDto(PostEntity pe) {
        return PostDto.builder()
                .id(pe.getId())
                .userName(pe.getUserName())
                .password(pe.getPassword())
                .email(pe.getEmail())
                .boardId(pe.getBoard().getId())
                .title(pe.getTitle())
                .content(pe.getContent())
                .status(pe.getStatus())
                .createdAt(pe.getCreatedAt())
                .build();
    }
}
