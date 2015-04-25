package edu.neu.cs5200.myCar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.myCar.models.*;
@Path("/user")
public class UserDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyCar");
	EntityManager em = null;
	
	//createUser
	public List<User> createUser(User user) {
		List<User> users = new ArrayList<User>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(user);
		Query query = em.createQuery("select user from User user");
		users = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return users;
	}

	//findUser
	public User findUser(Integer UserId){
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		User user = em.find(User.class, UserId);
		
		em.getTransaction().commit();
		em.close();
		
		return user;
	}
	
	public List<User> findUserByUsername(String username){
		List<User> users = new ArrayList<User>();
		
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
			Query query = em.createQuery("select user from User user where user.Username=?1");
			query.setParameter(1, username);
			users = query.getResultList();
			
			em.getTransaction().commit();
			em.close();
			return users;
	}
	
	//findAllUsers
	public List<User> findAllUsers(){
		List<User> users = new ArrayList<User>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select user from User user");
		users = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return users;
	}
	
	//updateUser
	public List<User> updateUser(@PathParam("id")Integer UserId, User user){
		List<User> users = new ArrayList<User>();
		CommentDao commentdao=new CommentDao();
		LikeDao likedao=new LikeDao();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		user.setUserId(UserId);
		List<Comment> c=commentdao.findCommentsbyUser(UserId);
		List<Like> l=likedao.findUser(UserId);
		List<Follower> fers=user.getFollowers();
		List<Follower> fs=user.getFollowings();
		user.setComments(c);
		user.setLikes(l);
		user.setFollowers(fers);
		user.setFollowings(fs);
		em.merge(user);
		
		Query query = em.createQuery("select user from User user");
		users = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return users;
	}
	//removeUser
	public List<User> removeUser(@PathParam("id")Integer UserId){
		List<User> users = new ArrayList<User>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		User user = em.find(User.class, UserId);
		em.remove(user);
		
		Query query = em.createQuery("select user from User user");
		users = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return users;
	}
	public void createComment(Integer userid, Comment comment){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		User user= em.find( User.class, userid);
		comment.setUser(user);
		user.getComments().add(comment);
		em.merge(user);
		em.getTransaction().commit();
	}
	
	
	//Like
	public void createLike(Integer userid,Like like){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		User user= em.find( User.class, userid);
		like.setUser(user);
		user.getLikes().add(like);
		em.merge(user);
		em.getTransaction().commit();
		
	}

	// Following followid follow followerid
	public void createFollowing(Integer followerid, Integer followid, Follower map) {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		User follower1 = em.find(User.class, followerid);
		User follow1 = em.find(User.class, followid);

		map.setFollower(follower1);
		map.setFollow(follow1);

		follower1.getFollowings().add(map);
		follow1.getFollowers().add(map);

		em.merge(follow1);
		em.merge(follower1);

		em.getTransaction().commit();
	}

	public Follower removeFollower(Integer id) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try {
			Follower follower = em.find(Follower.class, id);
			User user = follower.getFollow();
			user.getFollowers().remove(follower);

			User user1 = follower.getFollower();
			user1.getFollowings().remove(follower);

			em.remove(follower);

			em.getTransaction().commit();
			return follower;
		} catch (Exception e) {
		}
		return null;
	}
	
	public Follower findbyfollowerid(Integer followerid, List<Follower> followers) {
		for (int i = 0; i < followers.size(); i++) {
			User user= followers.get(i).getFollower();
			Integer id=user.getUserId();
			if (id==followerid) {
				return followers.get(i);
			}
		}
		return null;
	}
	
	

	public Integer Getremoveid( Integer followerid,Integer followid) {
		List<Follower> followers = new ArrayList<Follower>();
		User user = new User();
		Follower follower = new Follower();
		user = findUser(followid);
		followers = user.getFollowers();
		follower = findbyfollowerid(followerid, followers);
		try {
			return follower.getId();
		} catch (Exception E) {
		}
		// System.out.println(id);
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao dao = new UserDao();
		
//		List<User> users = dao.findUserByUsername("567");
//		System.out.println(users.size());
//		for(User user : users)
//			System.out.println(user.getUserId());
//		List<User> users = new ArrayList<User>();
//		Follower follow=new Follower();
		//dao.createFollowing(1,2,follow);//2follow1
	//	dao.removeFollower(47);
//		users=dao.findAllUsers();
//			System.out.println(users.get(1).getFollowers().size());
//		List<Follower> fans = new ArrayList<Follower>();
//		fans = dao.findUser(10).getFollowings();
//		System.out.println(fans.size());
//		if(fans.size() != 0){
//			int find = 0;
//			for(int i=0; i<fans.size(); i++){
//				if(Integer.toString(fans.get(i).getFollow().getUserId()).equals("1")){
//					find = find + 1;
//					System.out.println("find!");
//					System.out.println(fans.get(i).getFollow().getUserId());
//					break;
//				}
//				else{
//					System.out.println(fans.get(i).getFollow().getUserId()+"haha");
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
	}
}
