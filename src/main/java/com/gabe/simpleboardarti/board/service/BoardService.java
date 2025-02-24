package com.gabe.simpleboardarti.board.service;

import com.gabe.simpleboardarti.board.model.BoardDto;
import com.gabe.simpleboardarti.board.db.BoardEntity;
import com.gabe.simpleboardarti.board.db.BoardRepository;
import com.gabe.simpleboardarti.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService   {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDto create(
            BoardRequest boardRequest
    ) {
        var  entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var entityToSave = boardRepository.save(entity); // 실제 DB에 들어감.
        return boardConverter.toDto(entityToSave);
    }

    public BoardDto view(Long id) {
        var entity = boardRepository.findById(id).get(); // TODO: `.get()` 이 부분 존재 유무 체크 구현할 것.
        return boardConverter.toDto(entity);
    }
}
