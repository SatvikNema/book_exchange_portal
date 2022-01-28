package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Community;
import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.repository.CommunityRepository;
import com.satvik.bookexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

//    public User assignUserToCommunity(int community_id, int user_id) {
//        System.out.println("----------------------------------------------------------");
//        User user = userRepository.findById(user_id).get();
//        System.out.println("----------------------------------------------------------");
//        Community community = communityRepository.findById(community_id).get();
//
//        if(user.getUserXCommunities() == null){
//            user.setUserXCommunities(new HashSet<>());
//        }
//
//        user.getUserXCommunities().forEach((entry) -> {
//                Community userCommunity = entry.getCommunity();
//                System.out.println("community: "+userCommunity.getName());
//                if (userCommunity.getId() == community_id) {
//                    System.out.println("User is already a member of " + userCommunity.getName());
//                    return;
//                }
//        });
//
//        UserXCommunity userXCommunity = UserXCommunity.builder()
//                .community(community)
//                .user(user)
//                .userRole("USER")
//                .build();
//
//        user.getUserXCommunities().add(userXCommunity);
//        System.out.println("adding "+user.getEmail()+" to "+community.getName());
//        return userRepository.save(user);
//    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }
}
