package com.bsh.studycrudrestapi.controllers;

import com.bsh.studycrudrestapi.entities.ImageEntity;
import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.service.WriteService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "main")
public class writeController {

    private final WriteService writeService;

    @Autowired
    public writeController(WriteService writeService) {
        this.writeService = writeService;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getWrite() {
        ModelAndView modelAndView = new ModelAndView("home/write");
        return modelAndView;
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView postWrite(HttpServletRequest request, WriteEntity write,
                                  @RequestParam(value="files", required = false) MultipartFile[] files) throws IOException {
        if(files == null){
            files=new MultipartFile[0];
        }
        boolean result = this.writeService.putWrite(request,write,files);
        ModelAndView modelAndView = new ModelAndView();
        if(result){
            modelAndView.setViewName("redirect:/main/bulletin?index="+write.getIndex());
        }else{
            modelAndView.setViewName("home/write");
        modelAndView.addObject("result", result);
        }
        return modelAndView;
    }




//    에디터에 이미지 불러오기만 성공(그림 안보임) 그리고 바로 데이터베이스에 입력됨!
    @RequestMapping(value="/uploadImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postUploadImage(HttpServletRequest request,
                                  @RequestParam(value="upload") MultipartFile file) throws IOException {
        ImageEntity image = this.writeService.putImage(request,file);
        JSONObject responseObject = new JSONObject(){{
            put("url", "/main/downloadImage?index="+image.getIndex());
        }};
        return responseObject.toString();
    }

//    이미지의 그림까지 보이게 하는 코드
    @RequestMapping(value="downloadImage", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getDownloadImage(@RequestParam(value="index") int index){
        ImageEntity image = this.writeService.getImage(index);
        ResponseEntity<byte[]> response;
        if(image ==null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(image.getSize());
            headers.setContentType(MediaType.parseMediaType(image.getContentType()));
            response = new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        }
        return response;
    }



}
