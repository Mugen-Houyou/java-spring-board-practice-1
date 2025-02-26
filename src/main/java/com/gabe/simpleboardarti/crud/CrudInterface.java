package com.gabe.simpleboardarti.crud;

import com.gabe.simpleboardarti.common.Api;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

// 말 그대로 CRUD의 인터페이스를 작성해보자.
public interface CrudInterface<DTO> {

    DTO create(DTO t);

    Optional<DTO> read(Long id);

    DTO update(DTO dto);

    void delete(Long id);

    Api<List<DTO>> list(Pageable pageable);


}
