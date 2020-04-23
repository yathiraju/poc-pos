package com.pos.service;

import java.awt.Image;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.service.ImageService;

@Transactional
@Service
public class ImageServiceImpl implements ImageService{
    @Override
    public List<Image> getAllImages() {
        return null;
    }

    @Override
    public Image getImage(long imageId) {
        return null;
    }

    @Override
    public Image createImage(Image image) {
        return null;
    }

    @Override
    public void updateImage(Image image) {

    }

    @Override
    public void deleteImage(long ImageId) {

    }
}
