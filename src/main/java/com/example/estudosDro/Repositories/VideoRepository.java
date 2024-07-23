package com.example.estudosDro.Repositories;

import com.example.estudosDro.Entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
