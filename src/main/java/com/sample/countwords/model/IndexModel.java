package com.sample.countwords.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndexModel {

    private String keyword;
    private int maxChar;

}
