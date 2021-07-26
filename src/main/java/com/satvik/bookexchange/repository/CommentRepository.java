package com.satvik.bookexchange.repository;

import com.satvik.bookexchange.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
