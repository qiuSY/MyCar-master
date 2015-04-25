package edu.neu.cs5200.myCar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.PathParam;

import edu.neu.cs5200.myCar.models.Report;
import edu.neu.cs5200.myCar.models.User;

public class ReportDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyCar");
	EntityManager em = null;
	
	//createReport
	public List<Report> createReport(Report report) {
		List<Report> reports = new ArrayList<Report>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(report);
		Query query = em.createQuery("select report from Report report");
		reports = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return reports;
	}

	//findReport
	public Report findReport(Integer ReportId){
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Report report = em.find(Report.class, ReportId);
		
		em.getTransaction().commit();
		em.close();
		
		return report;
	}
	
	//findAllReports
	public List<Report> findAllReports(){
		List<Report> reports = new ArrayList<Report>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select report from Report report");
		reports = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return reports;
	}
	
	//removeReport
	public List<Report> removeReport(Integer ReportId){
		List<Report> reports = new ArrayList<Report>();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Report report = em.find(Report.class, ReportId);
		em.remove(report);
		
		Query query = em.createQuery("select report from Report report");
		reports = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return reports;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ReportDao dao = new ReportDao();
//		UserDao userdao = new UserDao();
//		User user = new User();
//		user = userdao.findUser(4);
//		Report report = new Report();
//		report.setReason("hahhaha");
//		report.setUser(user);
//		dao.createReport(report);
//		System.out.println(users.size());
//		
//	
//		
		
	}
}
