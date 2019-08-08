package com.viictrp.api.finance.server.api.converter;

import java.util.ArrayList;
import java.util.List;

public interface Converter<E, D> {

    E toEntity(D d);
    D toDto(E e);

    default List<E> toEntity(List<D> dtos) {
        List<E> entities = new ArrayList<>();
        if (dtos != null) {
            dtos.forEach(d -> entities.add(toEntity(d)));
        }
        return entities;
    }

    default List<D> toDto(List<E> entities) {
        List<D> dtos = new ArrayList<>();
        if (entities != null) {
            entities.forEach(e -> dtos.add(toDto(e)));
        }
        return dtos;
    }
}
