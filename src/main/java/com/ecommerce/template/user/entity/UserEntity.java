package com.ecommerce.template.user.entity;

import com.ecommerce.template.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String id;

    private String password;

    private String name;

    private LocalDateTime createTime;

    public static UserEntity of(User user) {
        return UserEntity.builder()
                .seq(user.getSeq())
                .id(user.getId())
                .password(user.getPassword())
                .name(user.getName())
                .createTime(user.getCreateTime())
                .build();
    }

    public User toDomain() {
        return User.builder()
                .seq(this.seq)
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .createTime(this.createTime)
                .build();
    }
}
