package cn.kee.core.action.blog;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.kee.core.entity.PageBean;
import cn.kee.core.entity.QueryCondition;
import cn.kee.core.entity.vo.BlogArticleVo;
import cn.kee.core.service.BlogArticleService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	private static Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private BlogArticleService blogArticleService;
	
	/**
	 * 根据分类获取文章列表
	 * @user jinhang
	 * 2015年6月11日
	 */
    @RequestMapping(value="/{categoryCode:[\\d]+}")  
    public String getArticleModel(HttpServletRequest request, @PathVariable int categoryCode, QueryCondition queryCondition) throws Exception{  
    	logger.info("categoryCode: " + categoryCode );
    	PageBean<BlogArticleVo> articleBean = null;
		try{
			articleBean = blogArticleService.getArticleList(categoryCode, queryCondition);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("articleList", articleBean != null ? articleBean.getDatas() : null);
		request.setAttribute("pageInfo", articleBean);
		request.setAttribute("categoryCode", categoryCode);
		logger.info("get article list size:" + articleBean.getDatas().size());
        return "blog/blog";  
    } 

}
