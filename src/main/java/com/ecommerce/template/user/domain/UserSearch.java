package com.ecommerce.template.user.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSearch {

    private String id;

    private String name;

    private LocalDate createdStartDate;

    private LocalDate createdEndDate;

    private List<User> list;
}
