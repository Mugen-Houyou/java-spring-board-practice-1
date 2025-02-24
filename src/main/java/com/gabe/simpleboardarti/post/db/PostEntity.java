package com.gabe.simpleboardarti.post.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabe.simpleboardarti.board.db.BoardEntity;
import com.gabe.simpleboardarti.reply.db.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // 테이블간 관계 성립 ==> Spring에서 객체지향으로 접근 및 구조 수정
    // private Long boardId;

    @ManyToOne // 즉, 나는 N개, 상대는 1개다.
    // 이렇게 할 경우, DB 접근할 때는 자동으로 `_id`를 붙인 `board_id` 컬럼을 찾는다.
    @JsonIgnore // 또한, board-post간 무한루프를 막기 위하여, post의 board는 JSON으로 뿌리지 않도록 막아야 한다.
    @ToString.Exclude // 마찬가지로 toString() 시 무한루프를 막기 위함.
    private BoardEntity board;

    private String userName;

    private String email;
    private String password;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdAt;
//
//    @Transient // DB의 실제 컬럼이 아님을 명시.
//    private List<ReplyEntity> listReplies = List.of(); // 디폴트르는 빈 배열을 리턴.

    @OneToMany(
            mappedBy = "post"
    )
    @Builder.Default
    private List<ReplyEntity> replyList = new ArrayList<>();
}
