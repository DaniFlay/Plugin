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
        Scanner s = new Scanner(stream);
        int count = 0;
        while(s.hasNext()){
            s.next();
            count++;
        }
        s.close();
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
        PDDocument pdf = PDDocument.load(stream);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(pdf);
        pdf.close();
        return text.split("\\s+").length;
    }

}
