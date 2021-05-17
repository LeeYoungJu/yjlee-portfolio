package com.yjlee.portfolio.service.posts;

import com.yjlee.portfolio.domain.posts.Posts;
import com.yjlee.portfolio.domain.posts.PostsRepository;
import com.yjlee.portfolio.web.dto.PostsResponseDto;
import com.yjlee.portfolio.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAll() {
        return postsRepository.findAll().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));
        postsRepository.delete(post);
    }
}
