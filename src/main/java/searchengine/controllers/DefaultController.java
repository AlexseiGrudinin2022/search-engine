package searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import searchengine.model.statuses.IndexingStatus;
import searchengine.model.Site;
import searchengine.services.SiteService;

import java.util.Date;

@Controller
public class DefaultController {

    /**
     * Метод формирует страницу из HTML-файла index.html,
     * который находится в папке resources/templates.
     * Это делает библиотека Thymeleaf.
     */

    @Autowired
    SiteService siteService;
    @RequestMapping("/")
    public String index() {
        //TODO для теста записи в таблицу
        Site site = new Site();
        site.setUrl("123");
        site.setName("asdas");
        site.setStatus(IndexingStatus.INDEXED);
        site.setStatusTime(new Date());
        site.setLastError("asdas");
        siteService.save(site);


        return "index";
    }
}
