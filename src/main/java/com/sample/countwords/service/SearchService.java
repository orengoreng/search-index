package com.sample.countwords.service;

import com.sample.countwords.exception.SearchIndexException;

import java.util.List;

public interface SearchService<T> {

    List<String> search(T t) throws SearchIndexException;
}
