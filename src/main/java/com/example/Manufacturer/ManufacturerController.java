package com.example.Manufacturer;

import com.example.Manufacturer.ManufacturerEntity.ManufacturerEntity;
import com.example.Manufacturer.config.AuthRequest;
import com.example.Manufacturer.config.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {


   @Autowired
   public ManufacturerService manufacturerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<ManufacturerEntity>> getAllManufacturers() {
        List<ManufacturerEntity> manufacturers = manufacturerService.getAllManufacturers();
        return ResponseEntity.ok(manufacturers);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> uploadData(@RequestBody ManufacturerEntity manufacturer) {
        try {
            manufacturerService.uploadData(manufacturer);
            return ResponseEntity.ok("Data uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload data: " + e.getMessage());
        }
    }

    @PostMapping("/process-invoice")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> processInvoice() {
        try {
            manufacturerService.processInvoice();
            return ResponseEntity.ok("Invoice processing completed.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to process invoices: " + e.getMessage());
        }
    }

    @GetMapping("/generate-report/{manufacturerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> generateReport(@PathVariable int manufacturerId) {
        try {
            String reportData = manufacturerService.generateReport(manufacturerId);
            return ResponseEntity.ok(reportData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to generate report: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return manufacturerService.addUser(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}


