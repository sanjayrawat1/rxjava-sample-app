package io.github.sanjayrawat1.reactivex.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.09.01
 */
@Data
public class PostDto {

    private Long id;

    private String title;

    private List<PostCommentDto> comments;
}
