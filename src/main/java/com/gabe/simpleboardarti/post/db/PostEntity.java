package com.gabe.simpleboardarti.post.db;

import com.gabe.simpleboardarti.reply.db.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name="post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    private String userName;

    private String email;
    private String password;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdAt;

    @Transient // DB의 실제 컬럼이 아님을 명시.
    private List<ReplyEntity> listReplies = List.of(); // 디폴트르는 빈 배열을 리턴.
}
