package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import sun.text.resources.CollationData;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;
import java.util.Collection;

@Log4j2
@WebServlet(name = "upload", value = "/upload" )
@MultipartConfig(fileSizeThreshold = 1024*1024)
public class UploadController extends HttpServlet { //무조건 post 방식.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uploadFolder = "C:\\upload";
        byte[] buffer = new byte[1024*8];
        //파일 업로드 시작
        Collection<Part> parts = req.getParts();

        parts.forEach(part -> {
//            log.info(part);

            String type = part.getContentType();
            if(type == null) {
                log.info("partName" + part.getName());
                return;
            }

            //밑에는 type일때만 실행. null 이 아니면 실행
            String fileName = part.getSubmittedFileName();
            // upload.jsp의  <input type="text" name="title" value="test....."> 의
            // name의 값이 title이라는 것을 알아야해서 getSubmittedFileName() 사용
            //getInputStream() // 처리할 수 있다.

            String uploadFileName = System.currentTimeMillis()+ "_" + fileName;

            log.info(fileName);

            //파일 업로드
            try (InputStream in = part.getInputStream(); //InputStream in = part.getInputStream() 자동으로 close() 처리
                 OutputStream fos = new FileOutputStream(uploadFolder + File.separator +uploadFileName);
                 //File.separator  경로를 만들어 준다.
            ) {
                while (true) {
                    int count = in.read(buffer);
                    if (count == -1) {break;}
                    fos.write(buffer,0,count);
                }

            }catch (Exception e){

            }//원본 파일 저장 끝

            //이미지 파일에 대해서만 섬네일 제장장
           if (type.startsWith("image")) {
               try {
                   Thumbnails.of(new File(uploadFolder + File.separator +uploadFileName))
                           .size(100,100)
                           .toFile( uploadFolder + File.separator + "s_" +uploadFileName);
               }catch (Exception e){

               }
            }


            log.info("-----------------------------------");
        }); // for each. 루프 돌고 있음

    }
}