package com.gabe.simpleboardarti.reply.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabe.simpleboardarti.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name="reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long postId;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private PostEntity post;

    private String userName ;
    private String password ;
    private String status ;
    private String title ;
    private String content ;
    private LocalDateTime createdAt;

}
