package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("community/{id}/assign/{user_id}")
    public ResponseEntity<String> assignUserToCommunity(@PathVariable("id") int community_id,
                                                        @PathVariable int user_id){
        communityService.assignUserToCommunity(community_id, user_id);
        String response = "assigned!";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
