package com.niit.TestCase;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;

public class BlogTestCase {
	static BlogDAO blogDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
    	
    	blogDAO=(BlogDAO)context.getBean("blogDAO");
    	
    }
    
    @Test
    public void addBlogTest()
    {
    	Blog blog=new Blog();
    	blog.setBlogName("core java");
    	blog.setBlogContext("Blog specific to core java");
    	blog.setLikes(1);
    	blog.setLoginname("Deepi");
    	blog.setStatus("A");
    	blog.setCreateDate(new java.util.Date());
    	
    	assertTrue("problem in blog insertion", blogDAO.addBlog(blog));
    	
    }
  /*  @Ignore
    @Test
    public void deleteBlogTest()
    {
    	assertTrue("Problem in blog deletion",blogDAO.deleteBlog(blog));
    }
    */
    
    @Ignore
    @Test
    public void rejectBlogTest()
    {
    	Blog blog=blogDAO.getBlog(0);
    	assertTrue("Problem in blog rejection",blogDAO.rejectBlog(blog));
    }
    @Ignore
    @Test
    public void approvalBlogTest()
    {
    	Blog blog=blogDAO.getBlog(0);
    	assertTrue("Problem in blog approval",blogDAO.approveBlog(blog));
    }
    
    @Test
    public void listBlogsByUserTest()
    {
    	List<Blog> listBlogs=blogDAO.listBlogs("Deepi");
    	assertTrue("Problem in listing the Blog",listBlogs.size()>0);
    	
    	for(Blog blog:listBlogs)
    	{
    		System.out.println(blog.getBlogName()+":::");
    		System.out.println(blog.getBlogContext()+":::");
    		System.out.println(blog.getLoginname()+":::");
    	}
    	
    }
    
    @Test
    public void incrementBlogLikeTest()
    {
    	Blog blog=blogDAO.getBlog(250);
    	assertTrue("Problem in incrementing likes",blogDAO.incrementLikes(blog));
    }
    
    @Test
    public void addCommentTest()
    {
    	BlogComment comment=new BlogComment();
    	comment.setCommentText("This blog is very informative");
    	comment.setLoginname("Vicky");
    	comment.setCommentDate(new java.util.Date());
    	assertTrue("Problem in insertion of Blog Comment ",blogDAO.addBlogComment(comment));
    }
    
    @Test
    public void listAllBlogCommentTest()
    {
    	List<BlogComment> listBlogComments= blogDAO.listBlogComments(0);
    	assertTrue("Problem in retrieving all the BlogComments",listBlogComments.size()>0);
    	
    	for(BlogComment blogComment:listBlogComments)
    	{
    		System.out.println(blogComment.getBlogId()+":::");
    		System.out.println(blogComment.getCommentText()+":::");
    		System.out.println(blogComment.getLoginname()+":::");
    	}
    }
    
}