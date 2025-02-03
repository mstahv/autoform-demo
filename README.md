# AutoForm demo

This is a usage example for the `AutoForm` component in the [Viritin add-on](https://vaadin.com/directory/component/flow-viritin). AutoForm is a Vaadin form component that is dynamically generated based on domain model. In this example it is applied to a simple demo backend and JPA entity model "stolen" from the popular CRUD UI Add-on, but basically any POJO (or record!) should do fine.

The Demo uses H2 in-memory database. To run the demo, check out the sources, import the project to your favourite Java IDE and run the Application class. The demo will be available at http://localhost:8080

The relevant code is in:

 * MainView, which contains the example form generation
 * MyAutoFormContext, which contains the (in this example) application wide configuration for form generation. This example uses mostly the defaults, but needs some hints for @ManyToOne and @ManyToMany relations, so that we can load available options for select components.

The UserGrid is "just a Vaadin grid for User objects", but utilizes slightly improved version from Viritin to maintain the property order based on the Java class. Highly suggested for RAD.

Everything else is copied from [the CRUD UI Add-on demo](https://github.com/alejandro-du/crudui/tree/master/crud-ui-demo), which is a bit older and slightly different kind of approach on the same idea (autogeneration of UI).
