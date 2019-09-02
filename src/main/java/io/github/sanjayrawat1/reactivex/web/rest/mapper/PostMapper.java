package io.github.sanjayrawat1.reactivex.web.rest.mapper;

import io.github.sanjayrawat1.reactivex.domain.Post;
import io.github.sanjayrawat1.reactivex.dto.PostDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link Post} and it's DTO called {@link PostDto}.
 *
 * @author Sanjay Singh Rawat
 * @since 2019.09.01
 */
@Mapper(componentModel = "jsr330", uses = { PostCommentMapper.class })
public interface PostMapper {

    PostDto toDto(Post post);

    List<Post> toDto(List<Post> posts);

    Post toEntity(PostDto postDto);
}
