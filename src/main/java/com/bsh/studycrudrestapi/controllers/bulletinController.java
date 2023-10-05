package com.bsh.studycrudrestapi.controllers;

import com.bsh.studycrudrestapi.entities.AttachmentEntity;
import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.enums.PatchWriteResult;
import com.bsh.studycrudrestapi.service.BulletinService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.Resource;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value="main")
public class bulletinController {

    private final BulletinService bulletinService;

    @Autowired
    public bulletinController(BulletinService bulletinService){
        this.bulletinService=bulletinService;
    }


    @RequestMapping(value="/bulletin", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getBulletin(@RequestParam(value="index") int index){
        ModelAndView modelAndView = new ModelAndView("home/bulletin");
        WriteEntity article = this.bulletinService.selectArticle(index);
        modelAndView.addObject("article", article);
        if(article !=null){
            modelAndView.addObject("attachments", this.bulletinService.getAttachmentsOf(article));
        }
        return modelAndView;
    }


    @RequestMapping(value="download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getDownload(@RequestParam(value="index")int index){
        AttachmentEntity attachment = this.bulletinService.getAttachment(index);
        ResponseEntity<byte[]> response;
        if(attachment == null){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(attachment.getFileSize());
            headers.setContentType(MediaType.parseMediaType(attachment.getFileContentType()));
            response = new ResponseEntity<>(attachment.getFileData(), headers, HttpStatus.OK);
        }
        return response;
    }


    @RequestMapping(value="delete", method = RequestMethod.DELETE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String delete(@RequestParam(value="index")int index){
        boolean result = this.bulletinService.deleteArticle(index);
        return String.valueOf(result);
    }
    @RequestMapping(value="bulletin/patch", method = RequestMethod.GET)
    public ModelAndView patchBulletin(@RequestParam(value="index")int index){
        WriteEntity article = this.bulletinService.getPatchBulletin(index);
        ModelAndView modelAndView = new ModelAndView("home/patchWrite");
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @RequestMapping(value="bulletin/patch",method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String PatchWrite(WriteEntity write){
        PatchWriteResult result = this.bulletinService.updateArticle(write);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result", result.name().toLowerCase());
        return responseObject.toString();
    }



}
