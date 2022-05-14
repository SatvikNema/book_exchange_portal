package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Community;
import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.entity.UserXCommunity;
import com.satvik.bookexchange.pojo.CommunityAddRequest;
import com.satvik.bookexchange.pojo.CommunityDto;
import com.satvik.bookexchange.repository.CommunityRepository;
import com.satvik.bookexchange.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Slf4j
public class CommunityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ModelMapper modelMapper;

    // todo add pre auth check so that only the creator/admin of the community can call this
    @Transactional
    public ResponseEntity<String> assignUserToCommunity(int communityId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Community community = communityRepository.findById(communityId).orElseThrow(NoSuchElementException::new);

        if(user.getUserXCommunities() == null){
            user.setUserXCommunities(new HashSet<>());
        }

        for(UserXCommunity entry: user.getUserXCommunities()){
            Community userCommunity = entry.getCommunity();
            if (userCommunity.getId() == communityId) {
                log.info("user {} already member of {}", user.getEmail(), community.getName());
                return new ResponseEntity<>("User already part of the community", HttpStatus.CONFLICT);
            }
        }

        UserXCommunity userXCommunity = UserXCommunity.builder()
                .community(community)
                .user(user)
                .userRole("USER")
                .build();

        user.getUserXCommunities().add(userXCommunity);
        userRepository.save(user);
        log.info("adding {} to {}", user.getEmail(), community.getName());
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public CommunityDto addCommunity(CommunityAddRequest communityAddRequest) {
        Community community = Community.builder()
                .city(communityAddRequest.getCity())
                .country(communityAddRequest.getCountry())
                .creator_id(communityAddRequest.getCreatorId())
                .latitude(communityAddRequest.getLatitude())
                .longitude(communityAddRequest.getLongitude())
                .name(communityAddRequest.getName())
                .state(communityAddRequest.getState())
                .numUsers(1)
                .userXCommunities(new HashSet<>())
                .posts(new HashSet<>())
                .build();

        Community savedCommunity = communityRepository.save(community);

        User author = userRepository.findById(communityAddRequest.getCreatorId()).orElseThrow(NoSuchElementException::new);
        UserXCommunity userXCommunity = UserXCommunity.builder()
                .user(author)
                .community(savedCommunity)
                .userRole("CREATOR")
                .build();
        if(author.getUserXCommunities() == null){
            Set<UserXCommunity> communityMap = new HashSet<>();
            communityMap.add(userXCommunity);
            author.setUserXCommunities(communityMap);
        } else {
            author.getUserXCommunities().add(userXCommunity);
        }

        User userSaved = userRepository.save(author);
        userSaved.getUserXCommunities().forEach(e -> log.info("User {} community {}", userSaved.getId(), e.getCommunity().getName()));
        return communityToDto(community);
    }

    @Transactional
    public ResponseEntity<String> removeFromCommunity(int communityId, int userId){
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Community community = communityRepository.findById(communityId).orElseThrow(NoSuchElementException::new);

        UserXCommunity mappingToDelete = user.getUserXCommunities().stream().filter(e -> e.getCommunity().equals(community)).findFirst().orElseThrow(NoSuchElementException::new);
        user.getUserXCommunities().remove(mappingToDelete);
        userRepository.save(user);
        return new ResponseEntity<>("Removed user from community", HttpStatus.OK);
    }

    private CommunityDto communityToDto(Community community){
        return modelMapper.map(community, CommunityDto.class);
    }
}
