package com.sanedge.implservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.implservice.exception.ResourceNotFoundException;
import com.sanedge.implservice.models.Tutorial;
import com.sanedge.implservice.payload.response.TutorialResponseDto;
import com.sanedge.implservice.repository.TutorialRepository;
import com.sanedge.implservice.utils.ModelToDto;

@Service
public class TutorialService {
    private final TutorialRepository tutorialRepository;

    private final ModelToDto modelToDto;

    @Autowired
    public TutorialService(TutorialRepository tutorialRepository, ModelToDto modelToDto) {
        this.tutorialRepository = tutorialRepository;
        this.modelToDto = modelToDto;
    }

    public List<TutorialResponseDto> getAll() {
        List<Tutorial> _tutorial = this.tutorialRepository.findAll();

        return this.modelToDto.tutorialToDtoTutorials(_tutorial);
    }

    public TutorialResponseDto getById(long id) {
        Tutorial _tutorial = this.tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No found tutorial"));

        return this.modelToDto.tutorialToDoTutorial(_tutorial);
    }

    public TutorialResponseDto createTutorial(Tutorial tutorial) {
        Tutorial _tutorial = new Tutorial();

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        _tutorial = tutorialRepository.save(_tutorial);

        return this.modelToDto.tutorialToDoTutorial(_tutorial);
    }

    public TutorialResponseDto updateById(long id, Tutorial tutorial) {
        Tutorial _tutorial = this.tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No found tutorial"));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        this.tutorialRepository.save(_tutorial);

        return this.modelToDto.tutorialToDoTutorial(_tutorial);
    }

    public boolean deleteTutorial(long id) {
        Tutorial _tutorial = this.tutorialRepository.findById(id).get();

        this.tutorialRepository.delete(_tutorial);

        return true;
    }

    public boolean deleteTutorialAll() {
        List<Tutorial> _tutorial = this.tutorialRepository.findAll();

        if (_tutorial.isEmpty()) {
            new ResourceNotFoundException("null");
        }
        this.tutorialRepository.deleteAll(_tutorial);

        return true;

    }

    public List<TutorialResponseDto> findByPublished() {
        List<Tutorial> _tutorial = this.tutorialRepository.findByPublished(true);

        if (_tutorial.isEmpty()) {
            new ResourceNotFoundException("empty tutorial by published");
        }

        return this.modelToDto.tutorialToDtoTutorials(_tutorial);
    }
}
