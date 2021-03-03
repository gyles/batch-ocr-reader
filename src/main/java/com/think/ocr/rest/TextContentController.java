package com.think.ocr.rest;

import com.think.ocr.mapper.TextContentMapper;
import com.think.ocr.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TextContentController {

    private final OcrService service;
    private final TextContentMapper mapper;

    @PutMapping(value = "/ocr",
        consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<TextContentDto>> readImage(@RequestPart("file") List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("File(s) must be provided.");
        }

        return ResponseEntity.ok(service.readAll(images).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList())
        );
    }

}
