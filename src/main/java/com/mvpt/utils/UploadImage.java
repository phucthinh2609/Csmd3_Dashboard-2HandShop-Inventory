package com.mvpt.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class UploadImage {
    public static String uploadImages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = null;

        for (Part part : req.getParts()) {

            String fn = part.getName();

            if (fn.equals("file")) {
                fileName = extractFileName(part);
                fileName = new File(fileName).getName();
                if (!fileName.isEmpty()) {
                    part.write(getFolderUploadServer(req).getAbsolutePath() + File.separator + fileName);
                    part.write(getFolderUploadLocal().getAbsolutePath() + File.separator + fileName);
                } else
                    fileName = "avatar-1.jpg";
                fileName = new File(fileName).getName();
                part.write(getFolderUploadServer(req).getAbsolutePath() + File.separator + fileName);
                part.write(getFolderUploadLocal().getAbsolutePath() + File.separator + fileName);
            }
        }

        return fileName;
    }


    private static File getFolderUploadServer(HttpServletRequest req) {
        String urlImages = req.getServletContext().getRealPath("/") + "assets\\images\\products\\";
//        System.out.println(urlImages);
//        System.out.println(System.getProperty("user.home") + "/Uploads");
        //File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        File folderUpload = new File(urlImages);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private static File getFolderUploadLocal() {
        String urlImages = "D:\\codegym\\code gym\\Module 3\\caseStudy\\dasboard\\src\\main\\webapp\\assets\\images\\users";
//        System.out.println(urlImages);
        //File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        File folderUpload = new File(urlImages);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private static String extractFileName(Part part) {
        String contenDisp = part.getHeader("content-disposition");
        String[] items = contenDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}

