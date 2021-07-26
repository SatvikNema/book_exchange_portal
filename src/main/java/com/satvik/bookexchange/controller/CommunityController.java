package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.entity.Community;
import com.satvik.bookexchange.pojo.CommunityResponse;
import com.satvik.bookexchange.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/communities")
    public ResponseEntity<List<CommunityResponse>> getCommunities(){
        List<Community> result = communityService.getAllCommunities();
        List<CommunityResponse> response = new ArrayList<>();
        result.forEach(community -> {
            response.add(CommunityResponse.builder()
                    .id(community.getId())
                    .name(community.getName())
                    .city(community.getCity())
                    .country(community.getCountry())
                    .creator_id(community.getCreator_id())
                    .latitude(community.getLatitude())
                    .longitude(community.getLongitude())
                    .numUsers(community.getNumUsers())
                    .state(community.getState())
                    .build());
        });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
