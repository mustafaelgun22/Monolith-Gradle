package com.sha.springbootmicro.ScheduledTasks;

import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Service.IProductservice;
import com.sha.springbootmicro.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Component
public class ScheduleProduct {
    private static final Logger log = LoggerFactory.getLogger(ScheduleProduct.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ProductService productService;

    @Scheduled(fixedDelay=2000L)
    public void getProduct() {
        //List<Product> products=productService.find_all_products();
        //System.out.println(products);
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
