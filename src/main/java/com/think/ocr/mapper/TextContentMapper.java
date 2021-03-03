package com.think.ocr.mapper;

import com.think.ocr.domain.TextContent;
import com.think.ocr.rest.TextContentDto;
import org.mapstruct.Mapper;

@Mapper
public interface TextContentMapper {
	
	TextContentDto toDto(TextContent textContent);

}
