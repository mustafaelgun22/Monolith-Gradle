package com.sha.springbootmicro.Controller;

import com.sha.springbootmicro.Model.Gallery;
import com.sha.springbootmicro.Service.IService;
import com.sha.springbootmicro.Service.ServiceEnum;
import com.sha.springbootmicro.Service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class GalleryController {

@Autowired
private ServiceFactory serviceFactory;

    @PostMapping("v1/gallery")
    public ResponseEntity<?> createGallery(@RequestBody Gallery gallery){
        IService service =serviceFactory.getService2(ServiceEnum.galleryService);
        return ResponseEntity.ok().body(service.saveEntity(gallery));
    }
}
