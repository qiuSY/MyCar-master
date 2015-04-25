package edu.neu.cs5200.myCar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.neu.cs5200.myCar.models.Comment;
import edu.neu.cs5200.myCar.models.Like;
import edu.neu.cs5200.myCar.models.User;



public class CommentDao {
	
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyCar");
	EntityManager em=factory.createEntityManager();
	
	public void createComment(Comment comment) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		
	}
	
	public List<Comment>findCommentsbystyleid(String styleid)
	{
		List<Comment> comments = new ArrayList<Comment>();
		TypedQuery <Comment> query=em.createQuery("select c from Comment c where c.styleid='"+styleid+"'",Comment.class);
		comments=query.getResultList();
		return comments;
	}
	
	public  List<Comment> findCommentsbyUser(Integer UserId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
			User user = em.find(User.class, UserId);
		
		em.getTransaction().commit();
		em.close();
		List<Comment> comments = new ArrayList<Comment>();
		comments=user.getComments();
		return comments;

	}
	
	public Comment findbystyleid(String styleid,List<Comment> comments)
	{
	       for (int i = 0; i < comments.size(); i++) {
	    	   String temp=comments.get(i).getStyleid();
    		   System.out.println(temp);
	    	   if(temp.equals(styleid))
	    	   {
	    		   System.out.println("dadsadsa");
	    		   
	    		   return comments.get(i);
	    		   }
         }
		return null;
	}	

	public List<Comment> removecomment(Integer id) {
		List<Comment> comments = new ArrayList<Comment>();

		em = factory.createEntityManager();
		em.getTransaction().begin();
//		Comment comment = new Comment();
		try {
			Comment comment = em.find(Comment.class, id);
			User user = comment.getUser();
			user.getComments().remove(comment);
			em.remove(comment);
			Query query = em.createQuery("select comment from Comment comment");
			comments = query.getResultList();

			em.getTransaction().commit();
			em.close();
			return comments;
		} catch (Exception e) {
		}
		return null;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		CommentDao dao=new CommentDao();
//		List<Comment> comments=dao.findCommentsbystyleid("8769");
//		for(Comment Comment:comments)
//			System.out.println(Comment.getTitle());
//		Comment comment = new Comment(null, "1", "2","dadas",null);
//		dao.createComment(comment);
//		List<Comment> comments=dao.findCommentsbyUser(1);
//		for (Comment comment:comments)
//		{
//			System.out.println(comment.getTitle());
//		}
//
//
		List<Comment> comments=dao.findCommentsbyUser(1);
		for (Comment comment:comments)
		{
			System.out.println(comment.getTitle());
		}

//		int id=commentdao.Getremoveid(1,"100813069");
//		System.out.println(id);
//
//		commentdao.removecomment(id);

	}

}