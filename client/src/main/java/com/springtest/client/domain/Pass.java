package com.springtest.client.domain;

import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pass {

    @EqualsAndHashCode.Include
    private int id;
    private String passNumber;
    private Timestamp expired;
}
