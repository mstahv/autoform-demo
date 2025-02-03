package org.demo;

import com.fasterxml.jackson.databind.JavaType;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.demo.entity.Group;
import org.demo.service.GroupService;
import org.demo.service.UserService;
import org.vaadin.firitin.rad.AutoFormContext;

@Component
@ApplicationScope
public class MyAutoFormContext extends AutoFormContext {

    public MyAutoFormContext(UserService userService, GroupService groupService) {

        // In the example couple of relations need a bit of special handling,
        // so that we can provide possible values for them from the backend
        // As a demo, we use two different kind of selection components for the groups

        withPropertyEditor(property -> {
            // The AutoFormContext (ab)uses reflection abstraction by the Jackson library
            // via you can do some flexible introspection to the beans and their properties
            JavaType primaryType = property.beanPropertyDefinition().getPrimaryType();
            // here we create a ComboBox if the edited property type happens to be Group
            if (primaryType.getRawClass() == Group.class) {
                return new ComboBox<Group>() {{
                    setItems(groupService.findAll());
                    setItemLabelGenerator(Group::getName);
                }};
            }
            // return null if we don't want to provide a custom editor for the property, defaults
            // and other selection rules are applied in the "filter chain"
            return null;
        });

        withPropertyEditor(property -> {
            // Here we create a CheckboxGroup for the groups property (List<Group>)
            // Selection here is simply based on a property name. Works here in simple example
            // but a bigger application might need more sophisticated logic.
            if (property.getName().equals("groups")) {
                return new CheckboxGroup<Group>() {{
                    setItems(groupService.findAll());
                    setItemLabelGenerator(Group::getName);
                }};
            }
            return null;
        });
    }
}
