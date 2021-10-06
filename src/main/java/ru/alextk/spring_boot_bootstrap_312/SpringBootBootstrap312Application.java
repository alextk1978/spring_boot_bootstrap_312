package ru.alextk.spring_boot_bootstrap_312;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.alextk.spring_boot_bootstrap_312.model.Role;
import ru.alextk.spring_boot_bootstrap_312.model.User;
import ru.alextk.spring_boot_bootstrap_312.repository.RoleRepository;
import ru.alextk.spring_boot_bootstrap_312.repository.UserRepository;


import java.util.HashSet;

@SpringBootApplication
public class SpringBootBootstrap312Application implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringBootBootstrap312Application(RoleRepository roleRepository,
                                             UserRepository userRepository,
                                             PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBootstrap312Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.save(admin);
        roleRepository.save(user);
        roleRepository.save(new Role("ROLE_GUEST"));

        userRepository.save(new User("Василий", "Уткин", 49, "admin@mail.com",
                passwordEncoder.encode("admin"),
                new HashSet<>() {{
                    add(admin);
                    add(user);
                }}));
        userRepository.save(new User("Дмитрий", "Губерниев", 46, "user@mail.com",
                passwordEncoder.encode("user"),
                new HashSet<>() {{
                    add(user);
                }}));
    }
}
