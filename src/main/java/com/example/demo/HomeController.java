package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showLandingPage(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User()); // Only add if not already present (e.g. after redirect)
        }
        return "index";
    }

    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("successMessage", "Sign-up successful!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Email already exists.");
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "redirect:/Home"; // 🔁 redirect so the @GetMapping("/Home") is triggered
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid credentials.");
            return "redirect:/";
        }
    }

    @PostMapping("/update")
    public String updateUser(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
        }

        return "redirect:/Home";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/Home";
    }

    @GetMapping("/Home")  // URL to visit for homepage
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "Home";  // Thymeleaf template Home.html (case-sensitive)
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";  // redirect to landing page or login page
    }

}
