package org.demo;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.demo.entity.User;
import org.demo.service.UserService;
import org.vaadin.firitin.components.button.VButton;
import org.vaadin.firitin.rad.AutoForm;
import org.vaadin.firitin.rad.AutoFormContext;

@Route
public class MainView extends VerticalLayout {

    private final UserService userService;
    private final AutoFormContext autoFormContext;
    private final UserGrid userGrid;

    public MainView(UserService userService, UserGrid userGrid, AutoFormContext autoFormContext) {
        this.userService = userService;
        this.autoFormContext = autoFormContext;
        this.userGrid = userGrid;

        add(new NewUserButton());
        addAndExpand(userGrid);

        userGrid.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                // If a user was selected, create a form for it and open in a dialog.
                // Alternatively you could show the editor in a new view or in a drawer.
                createForm(e.getValue()).openInDialog();
            }
        });
    }

    public AutoForm<User> createForm(User user) {
        // AutoFormContext can typically be application wide, shared by all users
        // and views. It contains configurations for e.g. custom property editors.
        // Check MyAutoFormContext for an example.
        AutoForm<User> form = autoFormContext.createForm(user);

        // To the generated form we then hook up the save, delete and reset handlers,
        // which are implemented in the service layer. Setting these handlers enables
        // related buttons in the form (but save only enabled if there is something to
        // save).
        form.setSaveHandler(u -> {
            userService.save(u);
            // We might want to do also something in the UI, here we update the grid
            // and focus the saved item (scroll to it).
            userGrid.refreshAndFocus(u);
        });
        form.setDeleteHandler(u -> {
            userService.delete(u);
            userGrid.list();
        });

        // Nothing to do in the backend as with Spring Data JPA we work with detached entities,
        //  just clear the selection in the UI/grid.
        form.setResetHandler(u -> userGrid.deselectAll());
        return form;
    }


    private class NewUserButton extends VButton {
        public NewUserButton() {
            super(VaadinIcon.PLUS, () -> {
                AutoForm<User> form = createForm(new User());
                // In this example all other actions (save/reset) are the same as for existing
                // entities, but disabling delete as this is not yet persisted.
                form.setDeleteHandler(null);
                form.openInDialog();
            });
        }
    }
}
