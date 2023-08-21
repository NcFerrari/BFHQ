package lp.be.jpa;

import lp.be.jpa.entity.*;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.fe.enums.NamespaceEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class EntityManager {

    private static volatile int countOfCall = 0;
    private final LoggerService logger = LoggerServiceImpl.getInstance(EntityManager.class);
    private SessionFactory factory;
    private Session session;

    protected EntityManager() {
        try {
            factory = new Configuration()
                    .configure(NamespaceEnum.HIBERNATE_CONFIG.getText())
                    .setProperty(NamespaceEnum.HIBERNATE_CONNECTION_URL.getText(), NamespaceEnum.JDBC.getText())
                    .addAnnotatedClass(ArmyEntity.class)
                    .addAnnotatedClass(AwardsEntity.class)
                    .addAnnotatedClass(Ip2nationEntity.class)
                    .addAnnotatedClass(KillsEntity.class)
                    .addAnnotatedClass(KitsEntity.class)
                    .addAnnotatedClass(MapinfoEntity.class)
                    .addAnnotatedClass(MapsEntity.class)
                    .addAnnotatedClass(PlayerEntity.class)
                    .addAnnotatedClass(PlayerHistoryEntity.class)
                    .addAnnotatedClass(RoundHistoryEntity.class)
                    .addAnnotatedClass(ServersEntity.class)
                    .addAnnotatedClass(UnlocksEntity.class)
                    .addAnnotatedClass(VehiclesEntity.class)
                    .addAnnotatedClass(WeaponsEntity.class)
                    .buildSessionFactory();
        } catch (Exception exp) {
            logger.getLog().error(NamespaceEnum.CANNOT_LOG_DB.getText());
        }
    }

    public synchronized Session getSession() {
        if (factory != null && (session == null || !session.isOpen())) {
            session = Objects.requireNonNull(factory).getCurrentSession();
            logger.getLog().info(String.format(NamespaceEnum.CALL_COUNT.getText(), ++countOfCall));
        }
        return session;
    }
}