package com.exemplo.simples.controller;

import com.exemplo.simples.services.PdfGenaratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GeneratePdfController {

    @Autowired
    private PdfGenaratorUtil util;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("name", "PDF");
        return "/home";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("name", "ComPDF");
            response.setContentType("application/pdf");
            util.createPdf("home", map, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
