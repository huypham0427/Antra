import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernatePersistenceProvider;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class JPAImplement {

    private DataSource getDataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("68706870");
        dataSource.setUrl("jdbc:mysql://localhost:/STUDENTS");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan();
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    private static final SessionFactory sessionFactory;
    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void main(String[] args) {
        JPAImplement jpaDemo = new JPAImplement();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
    }

    private static void insertToAuthors(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Authors a = new Authors();
        a.setFirst("Jerry");
        a.setLast("Hom");
        a.setId(17);
        em.merge(a);
        tx.commit();
    }

    private static void getAuthorsById(EntityManager em) {
        Query query = em.createQuery("select a from Authors a left join fetch a.bookDetails ts where s.id = ?1");
        query.setParameter(1, "17");
        Authors a = (Authors) query.getSingleResult();
        System.out.println(a);
    }

    private static void addToJunctionTable1(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Authors a = new Authors();
        a.setFirst("Jerry");
        a.setLast("Hom");
        Books b = new Books();
        //persist "b"(Books Object) first to get new id
        em.persist(b);
        b.setTitle("1th state");
        //build connection between "b" and "a"(Authors Object)
        BookDetails bd = new BookDetails();
        bd.setAuthors(a);
        bd.setBooks(b);
        b.addBookDetails(bd);

        em.persist(a);
        tx.commit();
    }

    private static void addToJunctionTable2(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNativeQuery("INSERT INTO BookDetails (A_ID, B_ID) VALUES (?, ?)");
        query.setParameter(1, 4);
        query.setParameter(2, 4);
        query.executeUpdate();
        tx.commit();
    }

    private static void addToJunctionTable3(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Authors a = em.find(Authors.class, "Henry");
        Books b = em.find(Books.class, "Hello 9");
        BookDetails bd = new BookDetails();
        bd.setAuthors(a);
        bd.setBooks(b);
        em.persist(bd);
        tx.commit();
    }

    private static void notOrphanRemoveBiRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select a from Authors a join fetch a.BookDetails bd where s.id = ?1");
        query.setParameter(1, "5");
        Authors a = (Authors) query.getSingleResult();
        Books b = em.find(Books.class, "3");
        List<BookDetails> book_details = new ArrayList<>();
        for(BookDetails bd: a.getBookDetails()) {
            if(bd.getAuthors().getId().equals(b.getId())) {
                book_details.add(bd);
                em.remove(bd);
            }
        }
        a.getBookDetails().removeAll(book_details);
        b.getBookDetails().removeAll(book_details);
        tx.commit();
    }
}
