package com.gabe.simpleboardarti.board.service;

import com.gabe.simpleboardarti.board.model.BoardDto;
import com.gabe.simpleboardarti.board.db.BoardEntity;
import com.gabe.simpleboardarti.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {
    private final PostConverter pc;

    public BoardDto toDto(BoardEntity be){
        var postDtos = be.getPostEntityList().stream()
                .map(postEntity -> {
                    return pc.toDto(postEntity);
                }).collect(Collectors.toList());

        return BoardDto.builder()
                .id(be.getId())
                .boardName(be.getBoardName())
                .status(be.getStatus())
                .postDtoList(postDtos)
                .build();
    }
}
