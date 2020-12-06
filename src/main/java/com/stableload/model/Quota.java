package com.stableload.model;

import lombok.Data;

@Data
public class Quota {
    String id;
    String parent_id;
    String body;
    Integer level;
}
