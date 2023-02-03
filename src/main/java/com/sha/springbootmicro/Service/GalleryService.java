package com.sha.springbootmicro.Service;

import com.sha.springbootmicro.Dto.GalleryDto;
import com.sha.springbootmicro.Model.Gallery;
import com.sha.springbootmicro.Repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GalleryService implements IService<GalleryDto,Gallery>{


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
    public Gallery saveEntity(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public List<Optional<Gallery>> filterByIds(List<Long> ids) {
        return null;
    }

    @Override
    public void deleteObjects(List<Long> ids) {

    }

    @Override
    public void deleteObject(Long id) {

    }

    @Override
    public List<Gallery> getAllObjects() {
        return null;
    }

    @Override
    public List<GalleryDto> get_objects_dto(List<Gallery> objects) {
        return null;
    }

    @Override
    public GalleryDto getDto(Long id) {
        return null;
    }

    @Override
    public void updateObject(Map<String, Object> updates, Optional<Gallery> optionalObject) {

    }

    @Override
    public Optional<Gallery> addAttributes(Long id, Gallery object, Map<String, Object> attributes) {
        return Optional.empty();
    }
}
