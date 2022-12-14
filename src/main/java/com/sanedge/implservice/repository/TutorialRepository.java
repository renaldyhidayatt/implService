package com.sanedge.implservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanedge.implservice.models.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}