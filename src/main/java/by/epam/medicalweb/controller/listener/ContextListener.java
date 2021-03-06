package by.epam.medicalweb.controller.listener;

import by.epam.medicalweb.pool.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
   public void contextInitialized (ServletContextEvent event){
       ConnectionPool.getInstance();
   }
   public void contextDestroyed(ServletContextEvent event){
       ConnectionPool.getInstance().closePool();
   }
}
