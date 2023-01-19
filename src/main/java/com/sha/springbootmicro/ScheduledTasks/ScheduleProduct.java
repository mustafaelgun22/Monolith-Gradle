package com.sha.springbootmicro.ScheduledTasks;

import com.sha.springbootmicro.Service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ScheduleProduct {
    private static final Logger log = LoggerFactory.getLogger(ScheduleProduct.class);

    private ProductService productService;

    public ScheduleProduct(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedDelay=2000L)
    public void getProduct() {

    }
}
