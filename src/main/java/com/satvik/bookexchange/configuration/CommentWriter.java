package com.satvik.bookexchange.configuration;

import com.satvik.bookexchange.entity.Comment;
import com.satvik.bookexchange.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CommentWriter implements ItemWriter<Comment> {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void write(List<? extends Comment> comments) throws Exception {
        log.info("Writing {} to {} comments to DB", comments.get(0).getEmail(), comments.get(comments.size()-1).getEmail());
        commentRepository.saveAll(comments);
    }
}
