package com.lisir.cn.service.impl;

import com.lisir.cn.dao.DictionaryRepository;
import com.lisir.cn.entity.Dictionary;
import com.lisir.cn.entity.ErrorCode;
import com.lisir.cn.exception.MyException;
import com.lisir.cn.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: liSir
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Override
    public Dictionary createDict(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    @Override
    public List<Dictionary> list() {
        return dictionaryRepository.findAll();
    }

    @Override
    public Dictionary queryDictById(Long dictId) {
        Optional<Dictionary> dictionary = dictionaryRepository.findById(dictId);
        if (!dictionary.isPresent()) {
            throw new MyException(ErrorCode.DICTIONARY_NOT_FOUND);
        }
        return dictionary.get();
    }

    @Override
    public Page<Dictionary> pageQueryDictionary(Pageable pageable) {
        return dictionaryRepository.findAll(pageable);
    }

    @Override
    public Dictionary updateDict(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    @Override
    public void deleteById(Long dictId) {
        boolean flag = dictionaryRepository.existsById(dictId);
        if (!flag) {
            throw new MyException(ErrorCode.DICTIONARY_NOT_EXIST);
        }
        dictionaryRepository.deleteById(dictId);
    }

}
