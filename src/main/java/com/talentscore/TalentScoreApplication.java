package com.talentscore;

import com.talentscore.entity.Employee;
import com.talentscore.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@SpringBootApplication
public class TalentScoreApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TalentScoreApplication.class, args);
    }

    /**
     * View Controller Router Configuration
     * Binds the root path "/" directly to your static index.html gateway page,
     * completely eliminating the default Whitelabel 404 screen.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    /**
     * Enterprise Data Seed Core Engine
     * Monitors the underlying JPA repository layer. If the data store is clean,
     * it initializes an Administrative profile and standard user baselines.
     */
    @Bean
    public CommandLineRunner databaseSeeder(EmployeeRepository empRepo) {
        return args -> {
            if (empRepo.count() == 0) {
                System.out.println("🚀 System memory database is empty! Generating core security profiles...");

                // Seed System Administrator Account
                Employee admin = new Employee("System Administrator", "admin@talentscore.com", "IT Infrastructure Core");
                admin.setRoleType("ADMIN");
                admin.setOpenToHiring(false);
                admin.setLifetimeCredits(BigDecimal.ZERO);
                empRepo.save(admin);

                // Seed Standard User Profile Match Portfolio Base
                Employee user = new Employee("Hariom Mishra", "hariom@mishra.com", "Java Backend Developer");
                user.setRoleType("USER");
                user.setOpenToHiring(true);
                user.setLifetimeCredits(new BigDecimal("50.00")); // Starter credit balance configuration
                empRepo.save(user);

                // Seed Additional Engineering Profile
                Employee user2 = new Employee("Amit Sharma", "amit@sharma.com", "Frontend UI Engineer");
                user2.setRoleType("USER");
                user2.setOpenToHiring(true);
                user2.setLifetimeCredits(new BigDecimal("35.50"));
                empRepo.save(user2);

                System.out.println("✅ Security role-based profile seeds successfully committed to memory database!");
            }
        };
    }

    /**
     * Global Cross-Origin Resource Sharing (CORS) Security Decoupling Configuration
     * Allows your decoupled local Python frontend server on port 3000 to cleanly
     * dispatch asynchronous operational HTTP fetch calls to this API port gateway.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Inside your corsConfigurer() bean:
                registry.addMapping("/api/**")
                        .allowedOrigins("https://your-frontend-app.vercel.app") // Swap this later with your live frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }


        };
    }
}