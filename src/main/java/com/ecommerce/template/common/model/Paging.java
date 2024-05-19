package com.ecommerce.template.common.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Paging<T> {

    private Integer page;

    private Integer size;

    private Long total;

    private List<T> list;

    public static <T> Paging<T> create(int page, int size, Long total, List<T> list) {
        return (Paging<T>) Paging.builder()
                .page(page)
                .size(size)
                .total(total)
                .list((List<Object>) list)
                .build();
    }
}
