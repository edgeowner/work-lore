package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71PoliticsJob    extends CommonJob {

    @Scheduled(cron = "0 26 12 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02002");
        category.setId(22L);
        category.setName("政治");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/politics/1.shtml",category);
        logger.info("...........end..........");
    }

}
