package org.demo;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;
import org.demo.entity.User;
import org.demo.service.UserService;
import org.vaadin.firitin.components.grid.GridSelect;

@SpringComponent
@Scope("prototype")
public class UserGrid extends GridSelect<User> {

    private final UserService userService;

    public UserGrid(UserService userService) {
        super(User.class);
        this.userService = userService;
        // Hide some columns that are not interesting in the grid
        removeColumnByKey("password");
        removeColumnByKey("groups");
        removeColumnByKey("mainGroup");
        setColumnReorderingAllowed(true);
        // Make all columns auto width, I wonder why this is no the default in Vaadin 🤔
        getColumns().forEach(column -> column.setAutoWidth(true));
        list();
    }

    public void refreshAndFocus(User toFocus) {
        setItems(userService.findAll());
        scrollToItem(toFocus);
    }

    public void list() {
        setItems(userService.findAll());
    }
}
