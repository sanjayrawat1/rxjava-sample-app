package io.github.sanjayrawat1.reactivex.service;

import io.github.sanjayrawat1.reactivex.domain.Post;
import io.github.sanjayrawat1.reactivex.dto.PostDto;
import io.github.sanjayrawat1.reactivex.repository.PostRepository;
import io.github.sanjayrawat1.reactivex.web.rest.mapper.PostMapper;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
@Slf4j
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Inject
    private PostMapper postMapper;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Single<PostDto> save(PostDto postDto) {
        log.debug("Creating Post : {}", postDto);
        Post post = postRepository.save(postMapper.toEntity(postDto));
        return Single.just(postMapper.toDto(post));
    }

    /*public Maybe<PostDto> findById(Long id) {
        log.debug("Find Post by id : {}", id);
        System.out.println("thread name before maybe emitter -> " + Thread.currentThread().getName());
        return Maybe.create(emitter -> {
            try {
                Optional<Post> postMaybe = postRepository.findById(id);
                System.out.println("thread name after maybe emitter -> " + Thread.currentThread().getName());
                if (postMaybe.isPresent()) {
                    Post post = postMaybe.get();
                    post.getComments();
                    emitter.onSuccess(postMapper.toDto(post));
                } else {
                    emitter.onComplete();
                }
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        });
    }*/

    public Optional<PostDto> findById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.map(post -> Optional.of(postMapper.toDto(post))).orElse(Optional.empty());
    }
}
