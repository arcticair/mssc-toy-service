package com.arcticair.mssctoyservice.model;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ToyPagedList extends PageImpl<ToyDTO> {
    public ToyPagedList(List<ToyDTO> content) {
        super(content);
    }

    public ToyPagedList(List<ToyDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
