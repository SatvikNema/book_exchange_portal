package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.entity.Comment;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class JobController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/commentsTest")
    public ResponseEntity<String> loadCommentsTest(){
        ResponseEntity<List<Comment>> comments = restTemplate.exchange("https://jsonplaceholder.typicode.com/comments"
        , HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {});
        String response = "loaded!";

        List<Comment> payload = comments.getBody();
        assert payload != null;
        payload.forEach(comment -> System.out.println(comment.getName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<String> loadComments() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));

        //todo replace this with the user running the job
        maps.put("ran-by", new JobParameter("satvik"));

        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        System.out.println("JobExecution object: "+jobExecution.toString());

        while(jobExecution.isRunning()){
            System.out.println("job is running...");
        }

        String response = "completed!";
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
