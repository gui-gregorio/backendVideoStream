package com.example.estudosDro.Repositories;

import com.example.estudosDro.Entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
