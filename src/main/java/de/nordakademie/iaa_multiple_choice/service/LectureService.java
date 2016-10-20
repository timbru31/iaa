package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.Course;
import de.nordakademie.iaa_multiple_choice.domain.CourseRepository;
import de.nordakademie.iaa_multiple_choice.domain.Lecture;
import de.nordakademie.iaa_multiple_choice.domain.LectureRepository;
import de.nordakademie.iaa_multiple_choice.domain.Room;
import de.nordakademie.iaa_multiple_choice.domain.RoomRepository;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final RoomRepository roomRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LectureService(final LectureRepository lectureRepository, final RoomRepository roomRepository,
            final CourseRepository courseRepository) {
        this.lectureRepository = lectureRepository;
        this.roomRepository = roomRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void saveLecture(final Lecture lecture, final String roomNaturalId, final String courseNaturalId) {
        final Room room = roomRepository.find(roomNaturalId);
        final Course course = courseRepository.find(courseNaturalId);
        lecture.setRoom(room);
        lecture.setCourse(course);
        lectureRepository.createLecture(lecture);
    }

    @Transactional(readOnly = true)
    public List<Lecture> listAll() {
        return lectureRepository.findAll();
    }
}
