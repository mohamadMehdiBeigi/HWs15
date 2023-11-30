package service.impl;

import base.repository.BaseRepository;
import base.repository.BaseRepositoryImpl;
import base.service.BaseServiceImpl;
import entity.Lesson;
import repository.LessonRepository;
import service.LessonService;

public class LessonServiceImpl extends BaseServiceImpl<Lesson, Long, LessonRepository> implements LessonService {


    public LessonServiceImpl(LessonRepository repository) {
        super(repository);
    }
}
