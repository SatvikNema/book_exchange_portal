package com.satvik.bookexchange.pojo;

import com.satvik.bookexchange.util.PostTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAddRequest {
    private int userId;
    private int communityId;
    private String title;
    private String description;
    private PostTypeEnum type;

}
