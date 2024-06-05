package com.ecommerce.template.user.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSearch {

    private String id;

    private String name;

    private LocalDate createdStartDate;

    private LocalDate createdEndDate;

    private Integer page;

    private Integer size;
}
