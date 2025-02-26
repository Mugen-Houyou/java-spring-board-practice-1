package com.gabe.simpleboardarti.board.db;

import com.gabe.simpleboardarti.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name="board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(
            mappedBy = "board" // PostEntity에 가면 해당 변수 (board)가 있어야 함.
    )  // 1개의 BoardEntity는 N개의 PostEntity를 가진다.
       // 반대로, PostEntity에 가서, 해당 부분을 @ManyToOne(mappedBy="post") 지정 필요.
    @SQLRestriction("status = 'REGISTERED'")
    @Builder.Default // Builder 패턴으로 생성 시 누락되지 않게 하기 위함.
    private List<PostEntity> postEntityList = List.of();

}
