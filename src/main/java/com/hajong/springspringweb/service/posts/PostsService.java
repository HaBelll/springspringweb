package com.hajong.springspringweb.service.posts;

import com.hajong.springspringweb.domain.posts.Posts;
import com.hajong.springspringweb.domain.posts.PostsRepository;
import com.hajong.springspringweb.web.dto.PostsListResponseDto;
import com.hajong.springspringweb.web.dto.PostsResponseDto;
import com.hajong.springspringweb.web.dto.PostsSaveRequestDto;
import com.hajong.springspringweb.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해줌
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id = "+ id));
        posts.Update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id = "+ id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        // postsRepository로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 후 list로 반환하는 메소드
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.  id=" + id));
        postsRepository.delete(posts);
    }

}
