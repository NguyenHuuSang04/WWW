package edu.iuh.fit.nguyenhuusang_tuan7_bringboot.services;

import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.entities.Comment;
import edu.iuh.fit.nguyenhuusang_tuan7_bringboot.reposities.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7_BringBoot
 * @Class: CommentService
 * @Tạo vào ngày: 10/22/2025
 * @Tác giả: Nguyen Huu Sang
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByProductId(Integer productId) {
        return commentRepository.findByProduct_Id(productId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}