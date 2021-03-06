package com.example.demo.repository;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

    Comment findCommentByCommentId(String commentId);

    List<Comment> getByIndentIdEquals(String indentId);

    List<Comment> getAllByEvaluatedIdEqualsAndCommentTypeEquals(String evaluatedId, int type);

//    List<Comment> getAllByEvaluatedIdEqualsOrEvaluatorIdEqualsAndCommentTypeEquals(String userId, String userid, int type);

    List<Comment> getAllByEvaluatorIdEqualsAndCommentTypeEquals(String evaluatorId, int type);

    List<Comment> getAllByEvaluatedIdEquals(String evaluatedId);

}
