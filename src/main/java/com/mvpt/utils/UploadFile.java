package com.mvpt.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

public class UploadFile {
//    public final static String URL_IMAGE = "/Users/macbookair/Desktop/image/";
    public final static String URL_IMAGE = "/Users/macbookair/Desktop/Codegym/Module3/CS3/Cs3-2HandShop-Inventory/src/main/webapp/assets/images/products/";


    //. (png)
    private static String getExtension(Part part) {
        String contenDisposition = part.getHeader("content-disposition");
        String[] items = contenDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.lastIndexOf("."), s.length() - 1);
            }
        }
        return null;
    }

    public static String uploadImagesServer(HttpServletRequest req) throws ServletException, IOException {
        String fileName = UUID.randomUUID().toString();
        String filePath = URL_IMAGE + fileName;
        for (Part part : req.getParts()) {
            if (part.getContentType() != null && part.getContentType().startsWith("image")) {
                String extension = getExtension(part);
                if (extension != null) {
                    fileName += extension;
                    part.write(filePath + extension);
                }
            }

        }
        return fileName;
    }

    public static boolean checkFile(HttpServletRequest request) throws ServletException, IOException {
        boolean check = false;
        for (Part part : request.getParts()) {
            if (part.getName().equals("file") && part.getSize() == 0) {
                check = true;
            }
        }
        return check;
    }
}
