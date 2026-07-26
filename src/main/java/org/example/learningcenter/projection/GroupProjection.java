package org.example.learningcenter.projection;

import org.example.learningcenter.entity.model.Teacher;
import org.example.learningcenter.entity.model.TimeTable;

public interface GroupProjection {
    String getId();
    String getName();
    String getRoom();
    Teacher getTeacher();
    TimeTable getTimeTable();
}
