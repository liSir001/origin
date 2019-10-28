package com.lisir.cn.dao;

import com.lisir.cn.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}
