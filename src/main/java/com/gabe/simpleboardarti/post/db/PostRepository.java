package com.gabe.simpleboardarti.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity,Long> {

    // SELECT * FROM post WHERE id = ? AND status = ? ORDER BY id DESC LIMIT 1;
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);
}
