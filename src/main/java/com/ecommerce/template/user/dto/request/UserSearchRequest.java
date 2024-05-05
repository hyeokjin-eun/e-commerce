package com.ecommerce.template.user.dto.request;

import com.ecommerce.template.user.domain.UserSearch;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {

    private String id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdEndDate;

    public UserSearch toDomain() {
        return UserSearch.builder()
                .id(this.id)
                .name(this.name)
                .createdStartDate(this.createdStartDate)
                .createdEndDate(this.createdEndDate)
                .build();
    }
}
