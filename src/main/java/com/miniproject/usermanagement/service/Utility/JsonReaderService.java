package com.miniproject.usermanagement.service.Utility;

import com.miniproject.usermanagement.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JsonReaderService<T> {

    List<T> readJsonArrayFromFile(MultipartFile file);
}
