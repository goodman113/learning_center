package org.example.learningcenter.mapper;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentCreateDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentDto;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.student.StudentDto;
import org.example.learningcenter.entity.dto.teacher.TeacherDto;
import org.example.learningcenter.entity.dto.timeTable.TimeTableDto;
import org.example.learningcenter.entity.dto.user.UserDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Enrollment;
import org.example.learningcenter.entity.model.Student;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.projection.EnrollmentProjection;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.repository.StudentRepository;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {
    final StudentMapper studentMapper;
    final GroupMapper groupMapper;
    final StudentRepository studentRepository;
    final GroupRepository groupRepository;

     public EnrollmentDto toDtoFromProjection(EnrollmentProjection projection){
         return new EnrollmentDto(
                 new StudentDto(
                         projection.getStudentId(),
                         new UserDto(
                                 projection.getStudentUserId(),
                                 projection.getStudentImageUrl(),
                                 projection.getStudentFullName(),
                                 projection.getStudentPhone(),
                                 projection.getStudentBirthDate(),
                                 projection.getStudentRole()
                         ),
                         projection.getParentPhone()
                 ),
                 new GroupDto(
                         projection.getGroupId(),
                         projection.getGroupName(),
                         projection.getRoom(),
                         new TeacherDto(
                                 projection.getTeacherId(),
                                 new UserDto(
                                         projection.getTeacherUserId(),
                                         projection.getTeacherImageUrl(),
                                         projection.getTeacherFullName(),
                                         projection.getTeacherPhone(),
                                         projection.getTeacherBirthDate(),
                                         projection.getTeacherRole()
                                         )

                         ),
                         new TimeTableDto(
                 projection.getTimeTableId(),
                 projection.getTimeTableDays(),
                 projection.getTimeTableStartTime(),
                 projection.getTimeTableEndTime()
                     ),
                         projection.getGroupStatus()
                 ),
                 projection.getEnrollmentDate()
         );
     }

    public EnrollmentDto toDto(Enrollment enrollment) {
        return new EnrollmentDto(
                studentMapper.toDto(enrollment.getStudent()),
                groupMapper.toDto(enrollment.getGroup()),
                enrollment.getEnrolledAt()
        );
    }

    public Enrollment toEntityFromCreate(EnrollmentCreateDto createDto) {
        return new Enrollment(
                studentRepository.findById(createDto.studentId()).orElseThrow(()-> RestException.restThrow(ErrorType.STUDENT_NOT_FOUND)),
                groupRepository.findById(createDto.groupId()).orElseThrow(()-> RestException.restThrow(ErrorType.GROUP_NOT_FOUND)),
        );
    }
}
