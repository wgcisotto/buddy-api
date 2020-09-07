package com.wgcisotto.buddy.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
@Slf4j
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        log.info("redirecting=swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}
