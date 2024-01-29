//package com.orderWithDataBase.config;
//
//import com.orderWithDataBase.service.UpdateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration
//@EnableScheduling
//public class SchedulerConfig {
//
//    @Autowired
//    UpdateService updateService;
//
//    @Scheduled(fixedRate = 60000) // Run every 1 min
//    public void myScheduledMethod() {
//        //System.out.println("Scheduled task executed at: " + System.currentTimeMillis());
//
//        updateService.getAllOrdersFromLocalDataBase();
//    }
//}
