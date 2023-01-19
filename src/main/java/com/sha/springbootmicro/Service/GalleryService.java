package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Model.Gallery;
import com.sha.springbootmicro.Model.Product;
import com.sha.springbootmicro.Repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public class GalleryService implements IService{


    @Qualifier("galleryRepository")
    private GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public Optional<Gallery> findById(Long id) {
        return galleryRepository.findById(id);
    }

    @Override
    public <T> T saveEntity(Product product) {
        return null;
    }
}
