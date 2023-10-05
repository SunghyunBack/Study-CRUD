package com.bsh.studycrudrestapi.controllers;

import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.models.PagingModel;
import com.bsh.studycrudrestapi.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class mainController {

    private final MainService mainService;

    @Autowired
    public mainController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView getMain(@RequestParam(value = "p", defaultValue = "1", required = false) int requestPage,
                                @RequestParam(value = "q", defaultValue = "", required = false) String searchQuery,
                                @RequestParam(value="category", defaultValue="", required = false)String category) {
        ModelAndView modelAndView = new ModelAndView("home/main");
        PagingModel pagingModel = new PagingModel(MainService.PAGE_COUNT,
                this.mainService.getCount(searchQuery,category)
                , requestPage);

        WriteEntity[] writes = this.mainService.getByPage(pagingModel, searchQuery,category);
        modelAndView.addObject("writes", writes);
        modelAndView.addObject("category", category);
        modelAndView.addObject("pagingModel", pagingModel);
        modelAndView.addObject("searchQuery", searchQuery);
        return modelAndView;
    }



    @RequestMapping(value = "main", method = RequestMethod.POST)
    public ModelAndView postMain(@RequestParam(value = "p", defaultValue = "1", required = false) int requestPage,
                                @RequestParam(value = "q", defaultValue = "", required = false) String searchQuery,
                                 @RequestParam(value="category", required = false)String category) {
        ModelAndView modelAndView = this.getMain(requestPage,searchQuery,category);
        return modelAndView;
    }



}
