package com.klu.HibernateCRUD; 
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.SessionFactory; 
import org.hibernate.Transaction; 
import org.hibernate.boot.Metadata; 
import org.hibernate.boot.MetadataSources; 
import org.hibernate.boot.registry.StandardServiceRegistry; 
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 
import org.hibernate.criterion.Restrictions; 
import java.util.List;
 
 
 
public class App  
{ 
    public static void main( String[] args ) 
    { 
        System.out.println( "Hello World!" ); 
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("Hibernate.cfg.xml").build(); 
        Metadata md = new MetadataSources().getMetadataBuilder(ssr).build(); 
         
        SessionFactory sf = md.getSessionFactoryBuilder().build(); 
        Session s = sf.openSession(); 
        Transaction t; 
         
        Student s1 = new Student(); 
        s1.setName("Sai"); 
        t = s.beginTransaction(); 
        s.save(s1); 
        t.commit(); 
        System.out.println("Inserted Data"); 
         
        Acceleration ac=new Acceleration(); 
        ac.setName("Student 1"); 
        ac.setCourse("JFSD"); 
        ac.setResult("PASS"); 
        t.begin(); 
        s.save(ac); 
        t.commit(); 
        System.out.println("Inserted acceleration Object"); 
         
        OddSemester odd=new OddSemester(); 
        odd.setName("Student 2"); 
        odd.setCourse("JFSD"); 
        odd.setRegistration("YES"); 
        t.begin(); 
        s.save(odd); 
        t.commit(); 
        System.out.println("Inserted OddSemester  Object");
         
        //Updation 
       /* Student s1 = s.find(Student.class, 16); 
        s1.setName("XYZ"); 
        t = s.beginTransaction(); 
        s.update(s1); 
        t.commit(); 
        System.out.println("Updated Student Successfully");*/ 
         
        //Deletion 
       // Student s1 = s.find(Student.class, 15); 
        //s1.setName("XYZ"); 
        t = s.beginTransaction(); 
        //s.update(s1); 
        //s.delete(s1); 
        t.commit(); 
        System.out.println("Deleted Student Object"); 
         
        Criteria c = s.createCriteria(Student.class); 
        c.add(Restrictions.gt("id", 3)); 
        List<Student> l =  c.list(); 
        for(Student s2 : l) 
        { 
         System.out.println("id: "+s2.getId() + ", name: "+s2.getName()); 
        }
        
        Query<Student> qry = s.createQuery("select ST from Student ST where ST.id>1", Student.class);
        List<Student> l1 =  qry.list(); 
        for(Student s3 : l1) 
        { 
         System.out.println("id: "+s3.getId() + ", name: "+s3.getName()); 
        }

    } 
}