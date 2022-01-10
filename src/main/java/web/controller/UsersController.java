package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    // открытие страницы авторизации
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // открытие страницы приветствия авторизованного пользователя
    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello, User!");
        messages.add("Your name is here.");
        messages.add("Your lastname is here.");
        messages.add("Here is your age.");
        messages.add("Here is your password.");
        model.addAttribute("messages", messages);
        return "user/user";
    }

    //получаем всех пользователь от dao и передаем их для отображения в представление
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/index";
    }

    //передаем пустого пользователя на форму
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    //добавляем пользователя, переданного с формы, в БД
    @PostMapping()
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/new";
        }
        userService.addUser(user.getName(), user.getLastName(), user.getAge(), user.getPassword());
        return "redirect:/";
    }

    //возвращает в форму пользователя по id
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.searchUser(id));
        return "/admin/edit";
    }

    //принимаем из формы нового пользователя и обновляем его по id в БД
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/admin/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/";
    }

    //удаляем из БД пользователя по id
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
