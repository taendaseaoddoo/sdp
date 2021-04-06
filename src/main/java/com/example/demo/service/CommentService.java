package com.example.demo.service;

import com.example.demo.bean.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository = null;

    @Transactional
    public void addComment(Comment comment) { commentRepository.save(comment); }

    @Transactional
    public List<Comment> getCommentByIndent(String indentId) { return commentRepository.getByIndentIdEquals(indentId); }

    @Transactional
    public Comment getCommentById(String commentId) { return commentRepository.findCommentByCommentId(commentId); }

    @Transactional
    public List<Comment> getGoodComment(String evaluatedId) { return commentRepository.getAllByEvaluatedIdEqualsAndCommentTypeEquals(evaluatedId, 0); }

    @Transactional
    public List<Comment> getBadComment(String evaluatedId) { return commentRepository.getAllByEvaluatedIdEqualsAndCommentTypeEquals(evaluatedId, 1); }

    @Transactional
    public List<Comment> getCommentByUser(String userId) {
        List<Comment> comments1 = commentRepository.getAllByEvaluatedIdEqualsAndCommentTypeEquals(userId,0);
        List<Comment> comments2 = commentRepository.getAllByEvaluatorIdEqualsAndCommentTypeEquals(userId,0);
        comments1.addAll(comments2);
        return comments1;
    }

    @Transactional
    public List<Comment> getAllComments(String evaluatedId) { return commentRepository.getAllByEvaluatedIdEquals(evaluatedId); }

}
