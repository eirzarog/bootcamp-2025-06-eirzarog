package dev.redbeans.bootcamp202506eirzarog.springapi.models.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatCriteria {

    private Long userId;
    private String username;
    private Instant from;
    private Instant to;
}
