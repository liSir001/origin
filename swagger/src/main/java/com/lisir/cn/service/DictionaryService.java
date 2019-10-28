package com.lisir.cn.service;

import com.lisir.cn.entity.Dictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictionaryService {

    Dictionary createDict(Dictionary dictionary);

    List<Dictionary> list();

    Dictionary queryDictById(Long dictId);

    Page<Dictionary> pageQueryDictionary(Pageable pageable);

    Dictionary updateDict(Dictionary dictionary);

    void deleteById(Long dictId);
}
