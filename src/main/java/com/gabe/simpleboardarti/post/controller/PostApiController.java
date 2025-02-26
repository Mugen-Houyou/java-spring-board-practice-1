package com.gabe.simpleboardarti.post.controller;

import com.gabe.simpleboardarti.common.Api;
import com.gabe.simpleboardarti.post.db.PostEntity;
import com.gabe.simpleboardarti.post.model.PostRequest;
import com.gabe.simpleboardarti.post.model.PostViewRequest;
import com.gabe.simpleboardarti.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    // CRUD (Create / Read / Update / Delete / List)

    @PostMapping("")
    public PostEntity create(
            @Valid
            @RequestBody
            PostRequest postRequest
    ){
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostEntity view(
            @Valid
            @RequestBody PostViewRequest postViewRequest
            ){
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public Api<List< PostEntity>> list(
            // TODO: size의 최대 크기 제한하는 방법 알아오기!
            @PageableDefault(page=0,size=10,sort="id",direction = Sort.Direction.DESC) // 0번부터 시작, 단위는 10개, id 기준 내림차순 (descending)
            Pageable pageable
    ){
        return postService.all(pageable);
    }

    @PostMapping("/delete")
    public PostEntity delete(
            @Valid
            @RequestBody PostViewRequest postViewRequest

    ){
        return postService.delete(postViewRequest);


    }
}
