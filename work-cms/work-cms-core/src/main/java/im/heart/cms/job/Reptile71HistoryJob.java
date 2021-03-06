package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71HistoryJob   extends CommonJob {

    @Scheduled(cron = "0 33 16 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02008");
        category.setId(28L);
        category.setName("历史");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/history/1.shtml",category);
        logger.info("...........end..........");
    }
}
