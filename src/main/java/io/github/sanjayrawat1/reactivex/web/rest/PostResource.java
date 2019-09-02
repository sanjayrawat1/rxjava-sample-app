package io.github.sanjayrawat1.reactivex.web.rest;

import io.github.sanjayrawat1.reactivex.domain.Post;
import io.github.sanjayrawat1.reactivex.dto.PostDto;
import io.github.sanjayrawat1.reactivex.response.ApiResponse;
import io.github.sanjayrawat1.reactivex.service.PostService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
@RestController
@RequestMapping("/api")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public Single<ResponseEntity<PostDto>> create(@RequestBody PostDto postDto) {
        return postService.save(postDto).subscribeOn(Schedulers.io())
                .map(dto -> ResponseEntity.created(URI.create("/api/post/" + dto.getId())).body(dto));
    }

    @GetMapping("/post/{id}")
    public Single<ApiResponse> findById(@PathVariable Long id) {
        final ApiResponse apiResponse = new ApiResponse(false);
        System.out.println("thread name before maybe emitter -> " + Thread.currentThread().getName());
        return Single.create((SingleOnSubscribe<ApiResponse>) emitter -> {
            try {
                Optional<PostDto> postDto = postService.findById(id);
                postDto.map(post -> {
                    apiResponse.setSuccess(true);apiResponse.setResponse(post);
                    return apiResponse;
                }).orElse(apiResponse);
                System.out.println("thread name after maybe emitter -> " + Thread.currentThread().getName());
                System.out.println(apiResponse.getResponse());
                emitter.onSuccess(apiResponse);
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        }).subscribeOn(Schedulers.io());

        /*postService.findById(id).subscribeOn(Schedulers.io()).subscribe(post -> {
            apiResponse.setSuccess(true);
            apiResponse.setResponse(post);
            Single.just(apiResponse);
        }, Throwable::printStackTrace, () -> Single.just(apiResponse));
        return Single.just(apiResponse);*/
    }
}
