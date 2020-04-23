package com.pos.service;

import java.awt.Image;
import java.util.List;

public interface ImageService {
    List<Image> getAllImages();
    Image getImage (long imageId);
    Image createImage(Image image);
    void updateImage(Image image);
    void deleteImage(long ImageId);
}
