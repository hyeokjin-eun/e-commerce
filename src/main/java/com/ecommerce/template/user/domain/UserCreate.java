package com.ecommerce.template.user.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreate {

    private String id;

    private String password;

    private String name;
}
