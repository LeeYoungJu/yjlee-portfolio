package com.yjlee.portfolio.web;

import com.yjlee.portfolio.service.posts.PostsService;
import com.yjlee.portfolio.web.dto.PostsResponseDto;
import com.yjlee.portfolio.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @GetMapping("/api/v1/posts")
    public List<PostsResponseDto> findPosts() {
        return postsService.findAll();
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void delete(@PathVariable Long id) {
        postsService.delete(id);
    }
}
