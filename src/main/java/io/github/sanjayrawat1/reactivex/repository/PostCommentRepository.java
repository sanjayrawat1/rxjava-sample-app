package io.github.sanjayrawat1.reactivex.repository;

import io.github.sanjayrawat1.reactivex.domain.PostComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
@Repository
public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
}
