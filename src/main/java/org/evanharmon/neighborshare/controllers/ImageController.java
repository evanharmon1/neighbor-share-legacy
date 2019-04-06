package org.evanharmon.neighborshare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("files")
public class ImageController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){

        return "image/index";
    }
}
