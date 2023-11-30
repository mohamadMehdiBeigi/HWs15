package repository.impl;

import base.repository.BaseRepositoryImpl;
import entity.Lesson;
import repository.LessonRepository;

public class LessonRepositoryImpl extends BaseRepositoryImpl<Lesson, Long> implements LessonRepository {

    @Override
    public Class<Lesson> getEntityClass() {
        return Lesson.class;
    }
}
