package com.example.EDMWebsite.Controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;

@Controller
@RequestMapping("/scrape")
public class JsoupController {

    @GetMapping("/hay")
    public String getHay(){
        return "scrape";
    }

    @PostMapping("/hay")
    public String scrapeHay() throws IOException {
//        final String url= "https://www.kijiji.ca/b-livestock/canada/hay/k0c217l0";
//
//        final Document document = Jsoup.connect(url).get();
//        Elements elements = document.getElementsByClass("price");
//        for(Element element : elements){
//            System.out.println(element.text());
//        }
        System.out.println("SUCCESSFUL POST CALL");

        return "scrape";
    }


}
