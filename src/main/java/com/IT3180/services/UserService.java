package com.IT3180.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IT3180.model.User;
import com.IT3180.model.Apartment;
import com.IT3180.model.Role;
import com.IT3180.dto.UserDTO;
import com.IT3180.repository.ApartmentRepository;
import com.IT3180.repository.RoleRepository;
import com.IT3180.repository.UserRepository;
import com.IT3180.repository.BillItemRepository;
import com.IT3180.util.TbConstants;
import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private ApartmentRepository apartmentRepository;
	
	@Autowired
	private BillItemRepository billItemRepository;
	
    public void saveUser(UserDTO userDto) 
	{
    	 if (userRepository.existsByName(userDto.getName())) {
             throw new RuntimeException("User with name '" + userDto.getName() + "' already exists!");
         }
    	 
    	 Apartment apartment = null;
    	    if (userDto.getApartment() != null && userDto.getApartment().getId() != null) {
    	        apartment = apartmentRepository.findById(userDto.getApartment().getId())
    	                .orElse(null);  // Nếu không tìm thấy, trả về null
    	    }
        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getRoles(),apartment);
        userRepository.save(user);
    }

    
    public User findUserByName(String name)
    {
        return userRepository.findByName(name);
    }

	
	public List<User> getAllUsers() 
	{
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	
	public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
	
	
	 public void addUser(User user) {
	        userRepository.save(user);
	    }
	 
	 public User getUserById(Long id) {
		 return userRepository.getById(id);
	 }
	 
	 public Long getTotalUser ()
	 {
		 return userRepository.count();
	 }
	 
	 public List<LocalDate> getDueDateById(Long id){
		 return billItemRepository.findDueDatesByUserId(id);
	 }
	 
	 public List<User> findByRole(String roleName) {
		    return userRepository.findUsersByRoles_Name(roleName);
		}

}
