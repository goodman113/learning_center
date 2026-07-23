package org.example.learningcenter.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "timetables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "timetable_days", joinColumns = @JoinColumn(name = "timetable_id"))
    @Column(name = "day")
    private List<String> days; // e.g., ["MONDAY", "WEDNESDAY", "FRIDAY"]

    private LocalTime startTime;
    private LocalTime endTime;
}