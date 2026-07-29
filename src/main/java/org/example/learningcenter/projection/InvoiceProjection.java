package org.example.learningcenter.projection;

import org.example.learningcenter.entity.enums.Days;
import org.example.learningcenter.entity.enums.GroupStatus;
import org.example.learningcenter.entity.enums.InvoiceStatus;
import org.example.learningcenter.entity.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface InvoiceProjection {
    String getId();
    String getInvoiceNumber();
    String getStudentUserId();
    String getStudentFullName();
    String getStudentPhone();
    LocalDate getStudentBirthDate();
    Role getStudentRole();
    String getParentPhone();
    String getGroupId();
    String getGroupName();
    String getGroupRoom();
    String getTeacherUserId();
    String getTeacherFullName();
    String getTeacherPhone();
    LocalDate getTeacherBirthDate();
    Role getTeacherRole();
    BigDecimal getAmount();
    LocalDateTime getIssuedAt();
    InvoiceStatus getStatus();
    String getTimeTableId();
    List<Days> getTimeTableDays();
    LocalTime getTimeTableStartTime();
    LocalTime getTimeTableEndTime();
    GroupStatus getGroupStatus();

}
