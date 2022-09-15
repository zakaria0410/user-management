package com.miniproject.usermanagement.service.Utility;

import java.util.List;

public interface JsonExporterService<T> {

    String export(List<T> tList);
}
