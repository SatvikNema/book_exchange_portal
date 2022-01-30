package com.satvik.bookexchange.repository;

import com.satvik.bookexchange.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
