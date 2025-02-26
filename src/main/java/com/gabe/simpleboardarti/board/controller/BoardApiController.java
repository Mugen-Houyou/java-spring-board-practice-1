package com.gabe.simpleboardarti.board.controller;

import com.gabe.simpleboardarti.board.model.BoardDto;
import com.gabe.simpleboardarti.board.model.BoardRequest;
import com.gabe.simpleboardarti.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardDto create(
        @Valid
        @RequestBody
        BoardRequest boardRequest
    ) {
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardDto view(
          @PathVariable Long id
    ){
        return boardService.view(id);
    }

}
