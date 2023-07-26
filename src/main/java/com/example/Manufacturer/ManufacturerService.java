package com.example.Manufacturer;

import com.example.Manufacturer.ManufacturerEntity.ManufacturerEntity;
import com.example.Manufacturer.ManufacturerEntity.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {


    @Autowired
    public ManufacturerRepository manufacturerRepository;

    @Autowired
    public UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<ManufacturerEntity> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public void uploadData(ManufacturerEntity manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public void processInvoice() throws Exception {
        List<ManufacturerEntity> manufacturers = manufacturerRepository.findAll();

        for (ManufacturerEntity manufacturer : manufacturers) {
            int stockLevel = manufacturer.getStockLevel();
            int orderedLevel = manufacturer.getOrderedLevel();

            if (orderedLevel > stockLevel) {
                throw new Exception("Ordered level is greater than stock level");
            } else {

                System.out.println("Manufacturer: " + manufacturer.getName());
                System.out.println("Stock Level: " + stockLevel);
                System.out.println("Ordered Level: " + orderedLevel);
            }
        }
    }

    public String generateReport(int id) {
        ManufacturerEntity manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manufacturer not found with ID: " + id));

        StringBuilder report = new StringBuilder();
        report.append("Report for Manufacturer with ID: ").append(id).append("\n");
        report.append("Name: ").append(manufacturer.getName()).append("\n");
        report.append("Location: ").append(manufacturer.getLocation()).append("\n");
        report.append("Description: ").append(manufacturer.getDescription()).append("\n");
        report.append("Price: ").append(manufacturer.getPrice()).append("\n");
        report.append("Stock Level: ").append(manufacturer.getStockLevel()).append("\n");
        report.append("Ordered Level: ").append(manufacturer.getOrderedLevel()).append("\n");


        return report.toString();
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }
}


