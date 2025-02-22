package com.gabe.simpleboardarti.post.controller;

import com.gabe.simpleboardarti.post.db.PostEntity;
import com.gabe.simpleboardarti.post.model.PostRequest;
import com.gabe.simpleboardarti.post.model.PostViewRequest;
import com.gabe.simpleboardarti.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

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
    public List< PostEntity> list(

    ){
        return postService.all();
    }

    @PostMapping("/delete")
    public PostEntity delete(
            @Valid
            @RequestBody PostViewRequest postViewRequest

    ){
        return postService.delete(postViewRequest);


    }
}
