package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.util.DownloadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class DownloadController {
    @GetMapping("/admin/download/common")
    public void downloadCommon(@RequestParam(name = "nameFile") String nameFile, HttpServletResponse response)
            throws IOException {
        File file = new File(nameFile);
        try {
            DownloadUtil.download(response, file);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin");
        }
    }
}
