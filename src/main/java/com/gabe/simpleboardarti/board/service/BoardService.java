package com.gabe.simpleboardarti.board.service;

import com.gabe.simpleboardarti.board.db.BoardEntity;
import com.gabe.simpleboardarti.board.db.BoardRepository;
import com.gabe.simpleboardarti.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService   {
    private final BoardRepository boardRepository;


    public BoardEntity create(
            BoardRequest boardRequest
    ) {
        var  entity=     BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        return boardRepository.save(entity); // 실제 DB에 들어감.
    }
}
