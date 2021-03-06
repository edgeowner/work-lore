package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71CommunityJob extends  CommonJob{

    @Scheduled(cron = "0 12 13 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02004");
        category.setId(24L);
        category.setName("社会");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/community/41.shtml",category);
        logger.info("...........end..........");
    }

}
