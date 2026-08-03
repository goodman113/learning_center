package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Enrollment;
import org.example.learningcenter.projection.EnrollmentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {

    @Query("""
        select
        e.id as id,
        s.id as studentId,
        su.id as studentUserId,
        su.imageUrl as studentImageUrl,
        su.fullName as studentFullName,
        su.phone as studentPhone,
        su.birthDate as studentBirthDate,
        su.role as studentRole,
        s.parentPhone as parentPhone,
        g.id as groupId,
        g.name as groupName,
        g.room as room,
        t.id as teacherId,
        tu.id as teacherUserId,
        tu.imageUrl as teacherImageUrl,
        tu.fullName as teacherFullName,
        tu.phone as teacherPhone,
        tu.birthDate as teacherBirthDate,
        tu.role as teacherRole,
        tt.id as timeTableId,
        tt.days as timeTableDays,
        tt.startTime as timeTableStartTime,
        tt.endTime as timeTableEndTime,
        g.status as groupStatus,
        e.enrolledAt as enrollmentDate
        from Enrollment e
        join e.student s
        join e.group g
        left join s.user su
        left join g.teacher t
        left join t.user tu
        left join g.timeTable tt
        where (:search is null
                or su.phone ilike cast(concat('%', :search, '%') as string)
                or su.fullName ilike  cast(concat('%', :search, '%') as string)
                or g.name ilike cast(concat('%', :search, '%') as string)
            )
""")
    Page<EnrollmentProjection> findEnrollmentsBySearch(String search, Pageable pageable);

    @Modifying
    @Query("""
        update Enrollment e set e.deleted=true where e.id = :id
""")
    void updateDeleted(String id);

    long countEnrollmentsByDeleted(Boolean deleted);
}
