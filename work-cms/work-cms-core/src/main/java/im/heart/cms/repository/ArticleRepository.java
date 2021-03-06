package im.heart.cms.repository;

import im.heart.cms.dto.ArticleProjection;
import im.heart.cms.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author gg
 * @desc 文章接口
 */
@Repository
public interface ArticleRepository extends QuerydslPredicateExecutor<Article>,  JpaRepository<Article, BigInteger>, JpaSpecificationExecutor<Article> {
    /**
     * 更新点击次数
     * @param id
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("UPDATE Article model SET hits=hits+1 WHERE model.id = :id")
    public void updateHitsById(@Param("id") BigInteger id);

    /**
     * 查询上一篇下一篇
     * @param id
     * @param categoryId
     * @return
     */
    @Query(nativeQuery = true,value = "(SELECT model.id AS id ,model.title AS title ,model.source AS source ,model.category_name AS categoryName ,model.summary AS summary ,model.hits AS hits,model.rate_times AS rateTimes ,model.push_time AS pushTime FROM cms_article model WHERE  model.id <:id  AND model.category_id = :categoryId AND model.is_pub = 1 ORDER BY  model.id DESC LIMIT 1)" +
            " UNION  (SELECT model.id AS id ,model.title AS title ,model.source AS source ,model.category_name AS categoryName ,model.summary AS summary ,model.hits AS hits,model.rate_times AS rateTimes ,model.push_time AS pushTime FROM cms_article model WHERE  model.id >:id   AND model.category_id = :categoryId AND model.is_pub = 1 ORDER BY  model.id  LIMIT 1)")
    public List<ArticleProjection> queryNearById(@Param("id") BigInteger id, @Param("categoryId") Long categoryId);

}
