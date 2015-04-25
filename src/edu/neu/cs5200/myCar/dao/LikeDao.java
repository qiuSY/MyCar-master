package edu.neu.cs5200.myCar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.neu.cs5200.myCar.models.Report;
import edu.neu.cs5200.myCar.models.User;
import edu.neu.cs5200.myCar.models.Like;

public class LikeDao {
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("MyCar");
	EntityManager em=factory.createEntityManager();
	
	
	public  List<Like> findUser(Integer UserId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try{
			User user = em.find(User.class, UserId);
		
		em.getTransaction().commit();
		em.close();
		List<Like> likes = new ArrayList<Like>();
		likes=user.getLikes();
		return likes;
		}
		catch(Exception e){}
		finally{}
		return null;
	}
	
	public Like findLikebystyleid(String styleid,List<Like> likes)
	{
	       for (int i = 0; i < likes.size(); i++) {
	    	   String temp=likes.get(i).getStyleid();
	    	   if(temp.equals(styleid))
	    	   {
	    		   return likes.get(i);
	    		   }
         }
		return null;
	}
	
	public int Getremoveid(Integer userid,String styleid)
	{
		List<Like> likes = new ArrayList<Like>();
		Like like=new Like();
		likes=findUser(userid);
		like=findLikebystyleid(styleid,likes);
		try {Integer id=like.getId();
		return id;
		}
		catch(Exception E){}
		return -1;
	}		

	public void createLike(Like like) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.merge(like);
		em.getTransaction().commit();
		em.close();	
	}	


	public Like removelike(Integer id) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try {			
			Like like = em.find(Like.class, id);
			User user = like.getUser();
			user.getLikes().remove(like);
			em.remove(like);
			em.getTransaction().commit();
			return like;
		} catch (Exception e) {
		}
		return null;
	}

	public static void main(String[] args) {
		LikeDao dao=new LikeDao();
//		// TODO Auto-generated method stub

//		dao.createLike(like);
	
		
//		Like like = dao.removelike(5);
//
//			System.out.println(like.getId());

//		Like like=new Like();
//		likes=dao.findUser(1);
//		like=dao.findLikebystyleid("1",likes);
//		int id=dao.Getremoveid(1,"1");
//		for(int i=0;i<2;i++)
//		{
//			int id=dao.Getremoveid(1,"2462");
//			Like like=new Like();
//			like.setIflike("hagaaa");
			
//			dao.updateLike (id,like);
//			dao.removelike (id);



//		System.out.println(like.getStyleid());

//		List<Like> likes = new ArrayList<Like>();
//		likes=dao.findUser(1);
//		Like like=new Like();
//		like=dao.findLikebystyleid("dadas",likes);
//		System.out.println(like.getStyleid());
//		if(like==null)
//			System.out.println("sdadasdas");
//		for(Like like : likes)
//		{
//			System.out.println(id);
//		}
	
//		List<Like> a = dao.findUser(10);
//		System.out.println(a.size());
//		if(a.size() != 0){
//			int find = 0;
//			for(int i=0; i<a.size(); i++){
//				if((find == 0) && a.get(i).getStyleid().equals("13776")){
//					find = find + 1;
//					System.out.println("find!");
//					break;
//				}
//				else{
//					continue;
//				}
//			}
//			if(find == 0){
//				System.out.println("this user don t like this style");
//			}
//		}
//		else{
//				System.out.println("sorry!");
//		}
//					
	}

}
