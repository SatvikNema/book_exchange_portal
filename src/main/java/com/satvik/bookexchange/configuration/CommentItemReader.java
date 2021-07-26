package com.satvik.bookexchange.configuration;

import com.satvik.bookexchange.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class CommentItemReader implements ItemReader<Comment> {

    private List<Comment> comments;
    private int nextCommentIndex;

    public CommentItemReader(){
        log.info("Calling the comments API....");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Comment>> commentsResponse = restTemplate.exchange("https://jsonplaceholder.typicode.com/comments"
                , HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {});

        comments = commentsResponse.getBody();
        comments = comments.subList(0, 50);
        log.info("Comments loaded. First comment email: {}", comments.get(0).getEmail());
        nextCommentIndex = 0;
    }

    @Override
    public Comment read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Comment nextComment = null;
        log.info("reading comment number {}", nextCommentIndex);
        if(nextCommentIndex < comments.size()){
            nextComment = comments.get(nextCommentIndex++);
        }else{
            nextCommentIndex = 0;
        }
        return nextComment;
    }
}
