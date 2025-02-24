package com.gabe.simpleboardarti.post.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gabe.simpleboardarti.board.db.BoardEntity;
import com.gabe.simpleboardarti.reply.db.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    private Long id;

    private Long boardId;

    private String userName;

    private String email;
    private String password;
    private String status;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
