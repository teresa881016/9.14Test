package com.sparta.test.dto;

import lombok.Getter;

@Getter
public class DeleteReponseDto {
    private String msg;

    public DeleteReponseDto(String msg){
        this.msg = msg;
    }
}