package ru.kds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kds.domain.Bug;
import ru.kds.domain.Role;
import ru.kds.domain.User;
import ru.kds.service.BugService;
import ru.kds.service.UserService;
import ru.kds.util.PagedList;
import ru.kds.util.Pager;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Set;

/**
 *
 */
@Controller
public class BugController {

    @Autowired
    private BugService bugService;

    @Autowired
    private UserService userService;

    private Validator bugValidator = new Validator() {
        @Override
        public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(User.class);
        }

        @Override
        public void validate(Object o, Errors errors) {
            Bug bug = (Bug)o;

            if (bug.getTitle() == null || bug.getTitle().isEmpty()) {
                errors.rejectValue("title", "errors.field-required", "Заполните поле");
            }
            if (bug.getText() == null || bug.getText().isEmpty()) {
                errors.rejectValue("text", "errors.field-required", "Заполните поле");
            }
            if (bug.getAssignee() == null) {
                errors.rejectValue("assignee", "errors.field-required", "Заполните поле");
            }
            if (bug.getReporter() == null) {
                errors.rejectValue("reporter", "errors.field-required", "Заполните поле");
            }
        }
    };

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.setDisallowedFields(new String[]{"created", "modified"});
        binder.registerCustomEditor(User.class, new PropertyEditorSupport(User.class) {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(userService.findOne(Long.valueOf(text)));
                } catch (NumberFormatException e) {}
            }
        });
    }

    @RequestMapping("/bugs")
    public String index(ModelMap model) {
        return index(1, model);
    }

    @RequestMapping("/bugs/{page}")
    public String index(@PathVariable Integer page, ModelMap model) {

        PagedList<Bug> bugs = bugService.findAll(page, 2);

        model.addAttribute("bugs", new Pager<Bug>(bugs.getList(), page, 2, bugs.getTotal()));

        return "bugs/index";
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return edit(null, model);
    }

    @RequestMapping(value = "/bugs/edit/{bugId}", method = RequestMethod.GET)
    public String edit(@PathVariable Long bugId, ModelMap model) {
        Bug bug = null;
        if (bugId != null) {
            bug = bugService.findOne(bugId);
        }
        if (bug == null) {
            bug = new Bug();
        }

        model.addAttribute("bug", bug);

        referenceData(model);

        return "bugs/edit";
    }

    @RequestMapping(value = "/bugs/add", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute Bug bug, ModelMap model, BindingResult result) {
        return submit(null, bug, model, result);
    }

    @RequestMapping(value = "/bugs/edit/{id}", method = RequestMethod.POST)
    public String submit(@PathVariable Long id, @ModelAttribute Bug bug, ModelMap model, BindingResult result) {

        bugValidator.validate(bug, result);
        if (result.hasErrors()) {
            referenceData(model);
            return "bugs/edit";
        }
        bugService.save(bug);
        return "redirect:/bugs";
    }

    private void referenceData(ModelMap model) {
        model.addAttribute("users", userService.findAll());
    }

    @RequestMapping("/bugs/delete/{id}")
    public String delete(@PathVariable Long id) {

        bugService.delete(id);

        return "redirect:/bugs";
    }
}
