package hibernate;

import lombok.Setter;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by rizhi-kote on 03.03.17.
 */
public class HandbokTopicDao {
    @Setter EntityManager manager;

    void method(){
        Session delegate = (Session) manager.getDelegate();
        Persistence.createEntityManagerFactory("");
    }
}
