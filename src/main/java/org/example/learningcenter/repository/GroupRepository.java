package org.example.learningcenter.repository;

import org.example.learningcenter.projection.GroupProjection;
import org.example.learningcenter.entity.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    boolean existsGroupByName(String name);

    @Query("""
        select g.id,
                g.name,
                g.room,
                g.teacher,
                g.timeTable
        from Group g
        where (:search is null or lower(g.name) like concat('%',lower(:search), '%') )
          and (:search is null or lower(g.room) like concat('%',lower(:search),'%') )
          and (:search is null or lower(g.teacher.user.fullName) like concat('%',lower(:search),'%') )
""")
    Page<GroupProjection> getAllByFilter(String search, Pageable pageable);

    @Query("""
        update Group g
        set g.deleted = true
        where g.id = :id
""")
    @Modifying
    void updateDeleted(String id);
}
