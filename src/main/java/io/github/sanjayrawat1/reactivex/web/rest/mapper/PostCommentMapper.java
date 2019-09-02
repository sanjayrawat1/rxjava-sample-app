package io.github.sanjayrawat1.reactivex.web.rest.mapper;

import io.github.sanjayrawat1.reactivex.domain.PostComment;
import io.github.sanjayrawat1.reactivex.dto.PostCommentDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link PostComment} and it's DTO called {@link PostCommentDto}.
 *
 * @author Sanjay Singh Rawat
 * @since 2019.09.01
 */
@Mapper(componentModel = "jsr330")
public interface PostCommentMapper {

    PostCommentDto toDto(PostComment comment);

    List<PostCommentDto> toDto(List<PostComment> comments);
}
