package com.satvik.bookexchange.configuration;

import com.satvik.bookexchange.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentProcessor implements ItemProcessor<Comment, Comment> {

    @Override
    public Comment process(Comment comment) throws Exception {
        if(comment.getPostId() == 2) comment.setName("Satvik Nema");
        return comment;
    }
}
