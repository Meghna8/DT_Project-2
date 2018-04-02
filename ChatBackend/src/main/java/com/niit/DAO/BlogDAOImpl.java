package com.niit.DAO;
import java.util.List;

//import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.model.Blog;
import com.niit.model.BlogComment;


public class BlogDAOImpl implements BlogDAO {

	@Autowired
    SessionFactory sessionFactory;
    
    @Transactional
	
	public boolean addBlog(Blog blog) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}


    public boolean deleteBlog(Blog blog) 
	{
		try
		{
		   sessionFactory.getCurrentSession().delete(blog);
		   return true;
		}
		catch(Exception e)
		{
			return false;
			
		}
		
	}

    @Transactional
   //	@Override
   	public boolean updateBlog(Blog blog) 
   	{
   		try
   		{
   			sessionFactory.getCurrentSession().update(blog);
   			return true;
   		}
   		catch(Exception e)
   		{
   		return false;
   		}

      }
       

  //  @Override
	public List<Blog> listBlogs(String username)
	{
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Blog where loginname=:username");
			query.setParameter("username", username);
			List<Blog> listBlogs=query.list();
			return listBlogs;
		}
		catch(Exception e)
		{
			return null;
        }
	}
	@Transactional
//	@Override
	public boolean approveBlog(Blog blog) 
	{
		try
		{   
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	@Transactional
	//@Override
	public boolean rejectBlog(Blog blog) 
	{
		try
		{   
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
 
	@Transactional
	//@Override
	public Blog getBlog(int blogId) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog=(Blog)session.get(Blog.class, blogId);
			return blog;
			
		}
		catch(Exception e)
		{
			return null;
		}
	}

	//@Override
	public List<Blog> listAllBlogs() 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Blog");
			List<Blog> listBlogs=query.list();
			return listBlogs;
		}
		catch(Exception e)
		{
			return null;
        }
	}

	//@Override
	@Transactional
	public boolean incrementLikes(Blog blog) 
	{
		try
		{
			int likes=blog.getLikes();
			likes++;
			blog.setLikes(likes);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}

	//@Override
	@Transactional
	public boolean addBlogComment(BlogComment blogComment) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
		
	}

//	@Override
	@Transactional
	public boolean deleteBlogComment(BlogComment blogComment) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
		
	}
	@Transactional
	//@Override
	public BlogComment getBlogComment(int CommentId) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			BlogComment blogComment=(BlogComment)session.get(BlogComment.class, CommentId);
			return blogComment;	
		}
		catch(Exception e)
		{
			return null;
		}
	}	

	//@Override
	public List<BlogComment> listBlogComments(int blogid) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from BlogComment where blogid=:blogid");
			query.setParameter("blogid",new Integer(blogid));
			List<BlogComment> listBlogComments=query.list();
			return listBlogComments;
		}
		catch(Exception e)
		{
			return null;
        }
		
	}

}