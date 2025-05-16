package com.sample.countwords.service;

import com.sample.countwords.model.IndexModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service("searchByKeyword")
public class SearchIndexService implements SearchService<IndexModel> {

    private final static String regex = "(?:(?<=^|[^a-zA-Z])'|'(?=[^a-zA-Z]|$)|[^a-zA-Z'])+";

    private final FileService fileService;

    /**
     * Search file for match keywords
     * @param index model criteria
     * @return array of hit results
     */
    @Override
    public List<String> search(@NotNull final IndexModel index) {
        String text = fileService.loadFile();
        var words = text.split(regex);
        var maxchar = index.getMaxChar();

        return Arrays.stream(words).filter(u -> (maxchar == 0 || u.trim().length() <= maxchar)
                        && u.toLowerCase().startsWith(index.getKeyword().toLowerCase()))
                .collect(Collectors.toList());
    }

}
