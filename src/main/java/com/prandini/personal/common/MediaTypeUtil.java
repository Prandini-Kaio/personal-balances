package com.prandini.personal.common;

import org.springframework.http.MediaType;

import java.io.File;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MediaTypeUtil {
    private static Map<String, MediaType> extensionTypeMap = new HashMap<>();

    public final static String TEXT_CSV = "text/csv";
    public final static MediaType TEXT_CSV_TYPE = new MediaType("text", "csv");

    public final static String XLS = "application/vnd.ms-excel";
    public final static MediaType APPLICATION_VND_MS_EXCEL = new MediaType("application", "vnd.ms-excel");

    static {
        extensionTypeMap.put("csv", TEXT_CSV_TYPE);
        extensionTypeMap.put("xls", APPLICATION_VND_MS_EXCEL);
        extensionTypeMap.put("xlsx", APPLICATION_VND_MS_EXCEL);
    }

    public static MediaType frowFile(File file){
        String type = URLConnection.guessContentTypeFromName(file.getName());
        if(type != null)
            return MediaType.parseMediaType(type);

        String[] splittedFile = file.getName().split("\\.");
        String extension = splittedFile[splittedFile.length-1];

        return extensionTypeMap.get(extension);
    }
}
