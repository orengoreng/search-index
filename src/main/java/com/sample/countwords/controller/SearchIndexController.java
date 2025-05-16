package com.sample.countwords.controller;

import com.sample.countwords.exception.SearchIndexException;
import com.sample.countwords.model.IndexModel;
import com.sample.countwords.service.FileService;
import com.sample.countwords.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "search-index", description = "API to search keyword in a file with documentation annotations")
public class SearchIndexController {

    private final Logger logger = LoggerFactory.getLogger(SearchIndexController.class);

    @Qualifier("searchByKeyword")
    private final SearchService<IndexModel> serviceSearch;

    private final FileService fileService;

    @Operation(summary = "Search keyword in file", description = "return hit words")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/search")
    private ResponseEntity<?>  searchText(@Parameter(name =  "keyword", description  = "search word matches in file", example = "m", required = true)
                                              @RequestParam(name = "keyword") String keyword,
                                          @Parameter(name =  "maxchar", description  = "filter result by char length; value 0, filter is disabled.", example = "0", required = false)
                                          @RequestParam(name = "maxchar", defaultValue = "0") int maxchar) {
        IndexModel index = IndexModel.builder().keyword(keyword).maxChar(maxchar).build();
        try {
            List<String> results = serviceSearch.search(index);
            return ResponseEntity.ok(results);
        } catch (SearchIndexException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(
            @Parameter(name =  "file", description  = "upload .txt file to search index", example = "context.txt", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            fileService.store(file);
            return ResponseEntity.ok("File successfully uploaded.");
        } catch (SearchIndexException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
