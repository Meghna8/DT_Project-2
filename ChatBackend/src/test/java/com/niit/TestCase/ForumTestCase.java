package com.niit.TestCase;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.ForumDAO;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

public class ForumTestCase {
static ForumDAO forumDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
    	
    	forumDAO=(ForumDAO)context.getBean("forumDAO");
    	
    }
    
    @Test
    public void addBlogTest()
    {
    	Forum forum=new Forum();
    	forum.setForumName("core java");
    	forum.setForumContext("Blog specific to core java");
    	forum.setLikes(0);
    	forum.setLoginname("Deepi");
    	forum.setStatus("A");
    	forum.setCreateDate(new java.util.Date());
    	
    	assertTrue("problem in Forum insertion", forumDAO.addForum(forum));
    	
    }
    @Ignore
    @Test
    public void deleteForumTest()
    {
    	Forum forum=forumDAO.getForum(0);
    	assertTrue("Problem in Forum deletion",forumDAO.deleteForum(forum));
    }
    
    @Ignore
    @Test
    public void rejectForumTest()
    {
    	Forum forum=forumDAO.getForum(0);
    	assertTrue("Problem in forum rejection",forumDAO.rejectForum(forum));
    }
    @Ignore
    @Test
    public void approvalForumTest()
    {
    	Forum forum=forumDAO.getForum(0);
    	assertTrue("Problem in forum approval",forumDAO.approveForum(forum));
    }
    
    @Test
    public void listForumsByUserTest()
    {
    	List<Forum> listForums=forumDAO.listForums("Deepi");
    	assertTrue("Problem in listing the Forum",listForums.size()>0);
    	
    	for(Forum forum:listForums)
    	{
    		System.out.print(forum.getForumName()+":::");
    		System.out.print(forum.getForumContext()+":::");
    		System.out.print(forum.getLoginname());
    	}
    	
    }
    
    @Test
    public void incrementForumLikeTest()
    {
    	Forum forum=forumDAO.getForum(50);
    	assertTrue("Problem in incrementing likes",forumDAO.incrementLikes(forum));
    }
    
    @Test
    public void addCommentTest()
    {
    	ForumComment comment=new ForumComment();
    	comment.setCommentText("This blog is very informative");
    	comment.setLoginname("Vicky");
    	comment.setCommentDate(new java.util.Date());
    	assertTrue("Problem in insertion of forum Comment ",forumDAO.addForumComment(comment));
    }
    
    @Test
    public void listAllForumCommentTest()
    {
    	List<ForumComment> listForumComments= forumDAO.listForumComments(0);
    	assertTrue("Problem in retrieving all the ForumComments",listForumComments.size()>0);
    	
    	for(ForumComment forumComment:listForumComments)
    	{
    		System.out.print(forumComment.getForumId()+":::");
    		System.out.print(forumComment.getCommentText()+":::");
    		System.out.print(forumComment.getLoginname());
    	}
    }


}
