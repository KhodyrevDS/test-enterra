package ru.kds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kds.domain.Role;
import ru.kds.domain.User;
import ru.kds.service.RoleService;
import ru.kds.service.UserService;
import ru.kds.util.PagedList;
import ru.kds.util.Pager;

import javax.validation.Valid;
import java.util.Set;

/**
 *
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Validator userValidator = new Validator() {
        @Override
        public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(User.class);
        }

        @Override
        public void validate(Object o, Errors errors) {
            User user = (User)o;

            if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
                errors.rejectValue("firstName", "errors.field-required", "Заполните поле");
            }
            if (user.getLastName() == null || user.getLastName().isEmpty()) {
                errors.rejectValue("lastName", "errors.field-required", "Заполните поле");
            }
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                errors.rejectValue("email", "errors.field-required", "Заполните поле");
            }
            if (user.getId() == null && (user.getPassword() == null || user.getPassword().isEmpty())) {
                errors.rejectValue("password", "errors.field-required", "Заполните поле");
            }

            if (user.getRoles() == null || user.getRoles().size() < 1) {
                errors.rejectValue("roles", "errors.field-required", "Выберите хотя бы одну роль");
            }

            if (!user.getEmail().matches("(\\w+)([\\.\\-\\+\\_]\\w+)*@([a-zA-Z_0-9\\-]+\\.)([a-zA-Z_0-9\\-]+)(\\.[a-zA-Z_0-9\\-]+)*")) {
                errors.rejectValue("email", "errors.email-invalid", "Неверный email");
            }

            User u = userService.findByEmail(user.getEmail());
            if (u != null && !u.getId().equals(user.getId())) {
                errors.rejectValue("email", "errors.email-exists", "Такой email уже существует");
            }
        }
    };

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Set.class, "roles", new CustomCollectionEditor(Set.class) {
            protected Object convertElement(Object element) {
                if (element instanceof Role) {
                    return element;
                }
                if (element instanceof String) {
                    return roleService.findOne(Long.valueOf(element.toString()));
                }
                return null;
            }
        });
    }

    @RequestMapping("/users")
    public String index(ModelMap model) {
        return index(1, model);
    }

    @RequestMapping("/users/{page}")
    public String index(@PathVariable Integer page, ModelMap model) {

        PagedList<User> users = userService.findAll(page, 10);

        model.addAttribute("users", new Pager<User>(users.getList(), users.getTotal(), page, 10));

        return "users/index";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return edit(null, model);
    }

    @RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.GET)
    public String edit(@PathVariable Long userId, ModelMap model) {
        User user = null;
        if (userId != null) {
            user = userService.findOne(userId);
        }
        if (user == null) {
            user = new User();
        }

        model.addAttribute("user", user);

        referenceData(model);

        return "users/edit";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute User user, ModelMap model, BindingResult result) {
        return submit(null, user, model, result);
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.POST)
    public String submit(@PathVariable Long id, @ModelAttribute User user, ModelMap model, BindingResult result) {

        userValidator.validate(user, result);
        if (result.hasErrors()) {
            referenceData(model);
            return "users/edit";
        }
        userService.save(user);
        return "redirect:/users";
    }

    private void referenceData(ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
    }

    @RequestMapping("/users/delete/{id}")
    public String delete(@PathVariable Long id) {

        userService.delete(id);

        return "redirect:/users";
    }
}
