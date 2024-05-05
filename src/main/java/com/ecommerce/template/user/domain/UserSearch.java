package com.ecommerce.template.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearch {

    private String id;

    private String name;

    private LocalDate createdStartDate;

    private LocalDate createdEndDate;

    private List<User> list;
}
