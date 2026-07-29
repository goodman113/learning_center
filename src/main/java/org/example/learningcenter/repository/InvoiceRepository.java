package org.example.learningcenter.repository;

import org.example.learningcenter.entity.enums.InvoiceStatus;
import org.example.learningcenter.entity.model.Invoice;
import org.example.learningcenter.projection.InvoiceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    @Query("""
        select
            i.id as id,
            i.invoiceNumber as invoiceNumber,
            s.id as studentId,
            su.id as studentUserId,
            su.fullName as studentFullName,
            su.phone as studentPhone,
            su.birthDate as studentBirthDate,
            su.role as studentRole,
            s.parentPhone as parentPhone,
            g.id as groupId,
            g.name as groupName,
            g.room as groupRoom,
            t.id as teacherId,
            tu.id as teacherUserId,
            tu.fullName as teacherFullName,
            tu.phone as teacherPhone,
            tu.birthDate as teacherBirthDate,
            tu.role as teacherRole,
            i.amount as amount,
            i.issuedAt as issuedAt,
            i.paymentStatus as status,
            tt.id as timeTableId,
            tt.days as timeTableDays,
            tt.startTime as timeTableStartTime,
            tt.endTime as timeTableEndTime,
            g.status as groupStatus
        from Invoice i
        join i.student s
        join s.user su
        left join s.group g
        left join g.teacher t
        left join t.user tu
        left join g.timeTable tt
        where i.deleted = false
        and (:search is null
            or su.fullName ilike concat('%', cast(:search as string), '%')
            or su.phone ilike concat('%', cast(:search as string), '%')
            or g.name ilike concat('%', cast(:search as string), '%')
            or i.invoiceNumber ilike concat('%', cast(:search as string), '%') )
        and (:from is null or i.issuedAt >= :from)
        and (:to is null or i.issuedAt <= :to)
        and (:status is null or i.paymentStatus = :status)
    """)
    Page<InvoiceProjection> getAllInvoicesByFilter(@Param("search") String search,
                                                   @Param("from") LocalDateTime from,
                                                   @Param("to") LocalDateTime to,
                                                   @Param("status") InvoiceStatus status,
                                                   Pageable pageable);

    boolean existsByInvoiceNumber(String invoiceNumber);
}
