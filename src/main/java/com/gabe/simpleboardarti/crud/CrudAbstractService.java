package com.gabe.simpleboardarti.crud;

import com.gabe.simpleboardarti.common.Api;
import com.gabe.simpleboardarti.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 이 서비스의 역할:
//   - DTO를 받음 ==> Entity로 변환 ==> 비즈니스 로직 수행 ==> DTO를 리턴
public abstract class CrudAbstractService<DTO, Entity> implements CrudInterface<DTO> {

    // @Autowired ==> 해당 ConverterInterface를 상속받은 bean이 있다면 여기가 채워질 것임. 없다면 Null값이 들어갈 것임.
    @Autowired(required = false)
    private ConverterInterface<DTO, Entity> converter;

    // save()을 해야 하므로.
    @Autowired(required = false)
    private JpaRepository<Entity,Long> jpaRepository;

    @Override
    public DTO create(DTO dto) {
        // DTO -> Entity
        var entity = converter.toEntity(dto);

        // Entity -> save
        jpaRepository.save(entity);

        // save -> DTO -> 리턴
        return converter.toDto(entity);
    }

    @Override
    public Optional<DTO> read(Long id) {
        var originalEntity = jpaRepository.findById(id);

        var dtoConverted = originalEntity.map(
            it -> {
                return converter.toDto(it);
            }
        ).orElseGet(()->null);

        return Optional.ofNullable(dtoConverted);
    }

    @Override
    public DTO update(DTO dto) {
        var originalEntity = converter.toEntity(dto);
        jpaRepository.save(originalEntity);
        return converter.toDto(converter.toEntity(dto));
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {
        var listFound = jpaRepository.findAll(pageable);

        var paginationBuilt = Pagination.builder()
                .page(listFound.getNumber())
                .size(listFound.getSize())
                .currentElements(listFound.getNumberOfElements())
                .totalElements(listFound.getTotalElements())
                .totalPages(listFound.getTotalPages())
                .build();

        var dtoList = listFound.stream()
                .map( it -> {
                    return converter.toDto(it);
                }).collect(Collectors.toList());

        return Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(paginationBuilt)
                .build();
    }
}
