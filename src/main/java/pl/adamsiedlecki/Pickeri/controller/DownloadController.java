package pl.adamsiedlecki.Pickeri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/download")
@Scope("prototype")
public class DownloadController {

    ServletContext context;

    @Autowired
    public DownloadController(ServletContext servletContext){
        this.context = servletContext;
    }

    @RequestMapping(value = "/pdf/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> download(@PathVariable("fileName") String fileName) throws IOException {
        System.out.println("Calling Download:- " + fileName);
        //ClassPathResource pdfFile = new ClassPathResource("downloads/" + fileName);

        File file = new File("src\\main\\resources\\downloads\\" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
//        headers.add("Access-Control-Allow-Headers", "Content-Type");
//        headers.add("Content-Disposition", "filename=" + fileName);
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0"); // headers.add("Expires", "0");
//
//        headers.setContentLength(pdfFile.contentLength());
//        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
//                new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
//        return response;
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);

    }
}
