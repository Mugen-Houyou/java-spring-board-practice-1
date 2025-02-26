package com.gabe.simpleboardarti.crud;

public interface ConverterInterface<DTO, Entity> {

    DTO toDto(Entity entity);

    Entity toEntity (DTO dto);

}
