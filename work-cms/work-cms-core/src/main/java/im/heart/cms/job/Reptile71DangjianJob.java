package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71DangjianJob  extends CommonJob {


    @Scheduled(cron = "0 36 17 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02006");
        category.setId(26L);
        category.setName("党建");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/dangjian/1.shtml",category);
        logger.info("...........end..........");
    }
}
