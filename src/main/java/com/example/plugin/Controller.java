package com.example.plugin;

import java.io.InputStream;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/plugin/wordcounter")
public class Controller {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/count")
    public int countWords(@RequestParam("file") MultipartFile file) throws Exception{
        InputStream stream = file.getInputStream();

        if (file.getOriginalFilename().endsWith(".txt")){
            return documentService.countWordsTXT(stream);
        }else if (file.getOriginalFilename().endsWith(".docx")){
            return  Math.toIntExact(documentService.countWordsDocx(stream));
        }else if(file.getOriginalFilename().endsWith(".pdf")){
            return documentService.countWordsPdf(stream);
        }else{
            throw new IllegalArgumentException("Formato de archivo incorrecto");
        }
    }
    
}
