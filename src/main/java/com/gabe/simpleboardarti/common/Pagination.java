package com.gabe.simpleboardarti.common;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination<T> {

    private Integer page;

    private Integer size;

    private Integer currentElements;

    private Integer totalPages;

    private Long totalElements;

}
