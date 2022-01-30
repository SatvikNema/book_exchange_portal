package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.entity.Community;
import com.satvik.bookexchange.pojo.CommunityAddRequest;
import com.satvik.bookexchange.pojo.CommunityDto;
import com.satvik.bookexchange.pojo.CommunityResponse;
import com.satvik.bookexchange.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("community/{id}/assign/user/{user_id}")
    public ResponseEntity<?> assignUserToCommunity(@PathVariable("id") int community_id,
                                                        @PathVariable int user_id){
        return communityService.assignUserToCommunity(community_id, user_id);
    }

    @GetMapping("community/{id}/remove/user/{user_id}")
    public ResponseEntity<?> removeUserFromCommunity(@PathVariable("id") int community_id,
                                                   @PathVariable int user_id){
        return communityService.removeFromCommunity(community_id, user_id);
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

    @PostMapping("/community")
    public CommunityDto addCommunity(@RequestBody CommunityAddRequest communityAddRequest){
        return communityService.addCommunity(communityAddRequest);
    }
}
