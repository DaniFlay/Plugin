package com.example.plugin;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.springframework.stereotype.Service;


@Service
public class DocumentService {
    public int countWordsTXT(InputStream stream){
        int count;
        try (Scanner s = new Scanner(stream)) {
            count = 0;
            while(s.hasNext()){
                s.next();
                count++;
            }
        }
        return count;
    }
    public long countWordsDocx(InputStream stream) throws Exception {
        XWPFDocument doc = new XWPFDocument(stream);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        String text = extractor.getText();
        extractor.close();
        return Arrays.stream(text.split("\\s+")).count();
    }
    public int countWordsPdf(InputStream stream) throws Exception{
        String text;
        try (PDDocument pdf = PDDocument.load(stream)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(pdf);
        }
        return text.split("\\s+").length;
    }

}
