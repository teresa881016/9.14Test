package com.sparta.test.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private String username;

}
