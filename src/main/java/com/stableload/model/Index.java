package com.stableload.model;

import lombok.Data;

@Data
public class Index {
    String id;
    String parent_id;
    String name;
    Integer level;
}
