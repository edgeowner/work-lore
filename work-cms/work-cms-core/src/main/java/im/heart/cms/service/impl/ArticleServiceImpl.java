package im.heart.cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import im.heart.cms.dto.ArticleDTO;
import im.heart.cms.dto.ArticleProjection;
import im.heart.cms.entity.Article;
import im.heart.cms.entity.QArticle;
import im.heart.cms.repository.ArticleRepository;
import im.heart.cms.service.ArticleService;
import im.heart.core.service.impl.CommonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service(value = ArticleService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class ArticleServiceImpl extends CommonServiceImpl<Article, BigInteger> implements ArticleService {
	protected static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	@Autowired
	private ArticleRepository articleRepository;
	@Override
	public List<Article> saveAll(Iterable<Article> entities) {
		return this.articleRepository.saveAll(entities);
	}

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	@Override
	public Page<Article> findAll(Specification<Article> spec, Pageable pageable){
		return this.articleRepository.findAll(spec,pageable);
	}
	@Override
	public Page<ArticleDTO> findAll(Predicate predicate, Pageable pageable){
		QArticle qArticle=QArticle.article;
		this.jpaQueryFactory.select(Projections.bean(ArticleDTO.class,qArticle.id,qArticle.allowComment));
		Page<Article> pag=this.articleRepository.findAll(predicate,pageable);
		System.out.println("@@@@@@@@@@@@@@"+JSON.toJSONString(pag));
		return null;
	}

  private  ArticleDTO build(ArticleProjection po){
	  ArticleDTO vo=ArticleDTO.builder().build();
	  vo.setId(po.getId());
	  vo.setTitle(po.getTitle());
	  vo.setPushTime(po.getPushTime());
	  vo.setType(po.getType());
	  vo.setHits(po.getHits());
	  vo.setAllowComment(po.getAllowComment());
	  return vo;
  }

	@Override
	public void updateHitsById(BigInteger id) {
		this.articleRepository.updateHitsById(id);
	}
	@Async
	@Override
	public void addUpdateHitsTask(BigInteger id) {
		this.updateHitsById(id);
	}
	@Override
	public List<ArticleDTO> queryNearById(BigInteger id, BigInteger categoryId) {
		List<ArticleProjection> projections=this.articleRepository.queryNearById(id,categoryId);
		List<ArticleDTO> list= Lists.newArrayList();
		for (ArticleProjection po:projections){
			ArticleDTO vo=build(po);
			list.add(vo);
		}
		return list;
	}

}
