package com.worddocs.controller;

import com.worddocs.service.WordDocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/word")
public class WordDocumentController {

    private final WordDocumentService wordDocumentService;

    public WordDocumentController(WordDocumentService wordDocumentService) {
        this.wordDocumentService = wordDocumentService;
    }

    @GetMapping("/generate-warning")
    public ResponseEntity<byte[]> generateWordDocument(@RequestParam String kreditKod) {
        try {
            byte[] document = wordDocumentService.generateWarning(kreditKod);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "ogoxlantirish_document.doc");

            return new ResponseEntity<>(document, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/generate-grafik")
//    public ResponseEntity<byte[]> generateGrafik(@RequestParam String kreditKod) {
//        try {
//            byte[] document = wordDocumentService.generateGrafik(kreditKod);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("attachment", "grafik_document.docx");
//
//            return new ResponseEntity<>(document, headers, HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

