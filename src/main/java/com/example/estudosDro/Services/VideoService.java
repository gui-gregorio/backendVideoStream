package com.example.estudosDro.Services;

import com.example.estudosDro.Entities.VideoEntity;
import com.example.estudosDro.Repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    public VideoEntity registerVideo(VideoEntity video){
        return videoRepository.save(video);
    }

    public List<VideoEntity> getVideos(){
        return videoRepository.findAll();
    }
}
