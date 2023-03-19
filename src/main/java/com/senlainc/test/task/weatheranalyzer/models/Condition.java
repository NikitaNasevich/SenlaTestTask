package com.senlainc.test.task.weatheranalyzer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Condition")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_id")
    private int conditionId;

    @Column(name = "text")
    private String text;

    @Column(name = "icon_link")
    @JsonProperty("icon")
    private String iconLink;

    @Column(name = "code")
    private int code;
}
