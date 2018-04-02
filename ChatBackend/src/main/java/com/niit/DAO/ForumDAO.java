package com.niit.DAO;

import java.util.List;

import com.niit.model.Forum;
import com.niit.model.ForumComment;

public interface ForumDAO {
	public boolean addForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public boolean updateForum(Forum forum);
	
	public List<Forum> listForums(String username);
	public boolean approveForum(Forum forum);
	public boolean rejectForum(Forum forum);
	public Forum getForum(int forumId);
	public List<Forum> listAllForums();
    public boolean incrementLikes(Forum forum);
	
	public boolean addForumComment(ForumComment forumComment);
	public boolean deleteForumComment(ForumComment forumComment);
	public ForumComment getForumComment(int commentId);
	public List<ForumComment> listForumComments(int forumid);
	



}