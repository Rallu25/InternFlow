package com.ibm.internflow.service;

import com.ibm.internflow.Transformer;
import com.ibm.internflow.dto.AttendanceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.internflow.repository.AttendanceRepository;

import java.util.List;
@Service

public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }
    public List<AttendanceDto> getAttendances() {
        return attendanceRepository.findAll().stream().map(Transformer::toDto).toList();
    }

    public AttendanceDto addAttendance(AttendanceDto attendanceDto) {
        var entity = Transformer.fromDto(attendanceDto);
        return Transformer.toDto(attendanceRepository.save(entity));
    }

    @Transactional
    public void deleteById(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }


}
