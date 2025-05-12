package com.IT3180.services;

import com.IT3180.model.Notifications;
import com.IT3180.repository.NotificationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class NotificationsServices {

	@Autowired
    private final NotificationsRepository notificationsRepository;

    public NotificationsServices(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public void save(Notifications notifications) {
        notificationsRepository.save(notifications);
    }

    // Phương thức xoá
    public boolean delete(Long id) {
        if (notificationsRepository.existsById(id)) {
            notificationsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Phương thức xóa thông báo cũ
    public void deleteExpiredNotifications() {
        LocalDateTime currentDate = LocalDateTime.now();
        notificationsRepository.deleteByCreatedAtBefore(currentDate.minusDays(60));
    }
    
    public List<Notifications> getAllNotifications(){
    	return notificationsRepository.findAll();
    }
}
