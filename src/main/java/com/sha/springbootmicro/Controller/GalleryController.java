package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Model.Gallery;
import com.sha.springbootmicro.Service.IService;
import com.sha.springbootmicro.Service.ServiceEnum;
import com.sha.springbootmicro.Service.ServiceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GalleryController {

    private IService service;

    private ServiceFactory serviceFactory;

    public GalleryController(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        this.service =serviceFactory.getService(ServiceEnum.galleryService);
    }

    @PostMapping("v1/gallery")
    public ResponseEntity<?> createGallery(@RequestBody Gallery gallery){
        return ResponseEntity.ok().body(service.saveEntity(gallery));
    }
}
