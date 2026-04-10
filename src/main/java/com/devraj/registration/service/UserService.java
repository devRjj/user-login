package com.devraj.registration.service;

import com.devraj.registration.entity.User;
import com.devraj.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    // Optional: if you want to support “find user by email”
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}




// package com.example.demo.service;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;

//     // @Autowired
//     // private PasswordEncoder passwordEncoder;

//     public User register(String email, String password) {
//         User user = new User();
//         user.setEmail(email);
//         user.setPassword(password);
//         // user.setPassword(passwordEncoder.encode(password));
//         return userRepository.save(user);
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(email);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found: " + email);
//         }
//         return org.springframework.security.core.userdetails.User
//             .withUsername(user.getEmail())
//             .password(user.getPassword())
//             .build();
//     }
// }