package com.stepashka.hibernate.mapper;

import com.stepashka.hibernate.dto.GoodImageDTO;
import com.stepashka.hibernate.entity.GoodImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface GoodImageMapper {
    //@Mapping(target = "image", source = "image", qualifiedByName = "toMultipartFile")
    GoodImageDTO toDTO(GoodImageEntity entity);

    //@Mapping(target = "image", source = "image", qualifiedByName = "toBytes")
    GoodImageEntity toEntity(GoodImageDTO dto);

//    @Named("toMultipartFile")
//    default MultipartFile toMultipartFile(byte[] file){
//        return new MockMultipartFile("good", file);
//    }
//
//    @Named("toBytes")
//    default byte[] toBytes(MultipartFile file) throws IOException {
//        return file.getBytes();
//    }
}
