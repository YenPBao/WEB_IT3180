package com.IT3180.services;

import com.IT3180.model.Complaint;
import com.IT3180.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class ComplaintService {
    @Autowired
    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository){
        this.complaintRepository = complaintRepository;
    }

    public void saveComplaint(Complaint complaint){
        complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints(){
        return complaintRepository.findAll();
    }
}
