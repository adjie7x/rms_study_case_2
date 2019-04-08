package com.mitrais.rms_study_case_2.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping(value = "/error")
    public ModelAndView handleError(HttpServletRequest request, Principal principal) {

        if (principal != null){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("error.html");

            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
            if (status != null) {

                Integer statusCode = Integer.valueOf(status.toString());

                if(statusCode == HttpStatus.NOT_FOUND.value()) {
                    mv.setViewName("404.html");
                    mv.addObject("pagetitle", "error.404.page.title");
                    mv.addObject("contenttitle","error.404.content.title");
                    return mv;
                }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error-500";
//            }
            }
        }



        return new ModelAndView("redirect:/");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
