package com.example.estudosDro.Controllers;


import com.example.estudosDro.Entities.VideoEntity;
import com.example.estudosDro.Services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @PostMapping("/register")
    public ResponseEntity<VideoEntity> registerVideo(@RequestBody VideoEntity video){
        VideoEntity savedVideo = videoService.registerVideo(video);
        return ResponseEntity.ok(savedVideo);
    }
    private static final String VIDEO_FOLDER = "C:\\Users\\guibe\\OneDrive\\Área de Trabalho\\java";
    @GetMapping("/{id}")
    public ResponseEntity<Resource> streamVideo(@PathVariable Long id) throws IOException {
        String videoPath = VIDEO_FOLDER + File.separator + id + ".mp4";
        Path path = Paths.get(videoPath);
        Resource resource = new FileSystemResource(path);
        if(!resource.exists()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(resource.contentLength());
        headers.setContentDispositionFormData("inline", path.getFileName().toString());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping()
    public List<VideoEntity> getVideos(){
        return videoService.getVideos();
    }


}
