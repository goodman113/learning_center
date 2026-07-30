package org.example.learningcenter.mapper;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.InvoiceCreateDto;
import org.example.learningcenter.entity.dto.InvoiceDto;
import org.example.learningcenter.entity.dto.InvoiceUpdateDto;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.student.StudentDto;
import org.example.learningcenter.entity.dto.teacher.TeacherDto;
import org.example.learningcenter.entity.dto.timeTable.TimeTableDto;
import org.example.learningcenter.entity.dto.user.UserDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.enums.InvoiceStatus;
import org.example.learningcenter.entity.model.Invoice;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.projection.InvoiceProjection;
import org.example.learningcenter.repository.StudentRepository;
import org.example.learningcenter.service.InvoiceNumberService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;
    final InvoiceNumberService invoiceNumberService;
    final GroupMapper  groupMapper;
    final TeacherMapper teacherMapper;

    public Invoice toEntity(InvoiceCreateDto createDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceNumberService.generateInvoiceNumber());
        invoice.setAmount(createDto.amount());
        invoice.setPaymentStatus(InvoiceStatus.PENDING);
        invoice.setIssuedAt(LocalDateTime.now());
        invoice.setStudent(studentRepository.findById(createDto.studentId())
                .orElseThrow(() -> RestException.restThrow(ErrorType.STUDENT_NOT_FOUND)));
        return invoice;
    }


    public InvoiceDto toDto(Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                studentMapper.toDto(invoice.getStudent()),
                groupMapper.toDto(invoice.getStudent().getGroup()),
                invoice.getAmount(),
                invoice.getIssuedAt(),
                invoice.getPaymentStatus()
        );
    }

    public InvoiceDto toDtoFromProjection(InvoiceProjection projection) {
        return new InvoiceDto(
                projection.getId(),
                projection.getInvoiceNumber(),
                new StudentDto(
                        projection.getStudentId(),
                        new UserDto(
                                projection.getStudentUserId(),
                                projection.getImageUrl(),
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
                        projection.getGroupRoom(),
                        new TeacherDto(
                                projection.getTeacherId(),
                                new UserDto(
                                projection.getTeacherUserId(),
                                projection.getImageUrl(),
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
                projection.getAmount(),
                projection.getIssuedAt(),
                projection.getStatus()
        );
    }

    public void mapUpdate(Invoice invoice, InvoiceUpdateDto updateDto) {
        if (updateDto.status() != null)
            invoice.setPaymentStatus(updateDto.status());
    }
}
