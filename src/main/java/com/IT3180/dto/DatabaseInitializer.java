package com.IT3180.dto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.IT3180.model.Role;
import com.IT3180.model.User;
import com.IT3180.repository.RoleRepository;
import com.IT3180.repository.UserRepository;
import com.IT3180.util.TbConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) 
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) 
    {
        if (userRepository.findByName("Admin_1") == null) 
        {
            Role adminRole = roleRepository.findByName(TbConstants.Role.ADMIN);
                    if (adminRole == null)
                    {
                        adminRole = new Role();
                        adminRole.setName(TbConstants.Role.ADMIN);
                        adminRole = roleRepository.save(adminRole);
                    };

            // Tạo user admin mới
            User admin = new User();
            admin.setName("Admin_1");
            admin.setEmail("admin1@example.com");
            admin.setPassword(passwordEncoder.encode("admin1")); 
            List<Role> roles = new ArrayList();
            roles.add(adminRole);
            admin.setRoles(roles);
            // Lưu admin vào database
            userRepository.save(admin);
            System.out.println("✅ Admin account created successfully!");
        }
    }
}
