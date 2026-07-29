package org.example.learningcenter.repository;

import org.example.learningcenter.projection.GroupNameProjection;
import org.example.learningcenter.projection.GroupProjection;
import org.example.learningcenter.entity.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    boolean existsGroupByName(String name);

    @Query("""
                select g
                from Group g
                where (:search is null or g.name ilike concat('%', cast(:search as string), '%')
                or g.room ilike concat('%', cast(:search as string), '%')
                or (g.teacher is not null and g.teacher.user.fullName ilike concat('%', cast(:search as string), '%')))
            """)
    Page<GroupProjection> getAllByFilter(@Param("search") String search, Pageable pageable);

    @Query("""
                    update Group g
                    set g.deleted = true
                    where g.id = :id
            """)
    @Modifying
    void updateDeleted(String id);

    @Query(value = "select count(id) from groups where deleted=false", nativeQuery = true)
    Optional<Integer> getCount();

    @Query("SELECT g.name,g.id from Group g where g.teacher.user.id = :userId")
    List<GroupNameProjection> findAllGroupNames(@Param("userId") String teacherId);

    @Query("SELECT g FROM Group g WHERE g.teacher.user.id = :userId AND g.status = 'ONGOING'")
    List<Group> findAllByTeacherUserId(@Param("userId") String userId);
}
