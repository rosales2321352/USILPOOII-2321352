package controllers.person;

import models.person.Person;
import views.core.combobox.CustomComboBox;
import views.person.PersonView;
import views.person.partials.PersonList;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class PersonController {

    final private PersonView panel;

    private int employee_id=0;
    final String[] COLUMN_NAMES = { "Id","Tipo_Documento","Documento","Nombre","Direccion","Email","Telefono","Referencias","Acciones"};

    public PersonController(PersonView panel) { this.panel = panel; }

    public void renderObjects() { this.panel.personList.makeTableHeader(COLUMN_NAMES);}

    public void loadDataTableAsync(String query){
        CompletableFuture<List<Person>> futureTablePerson = CompletableFuture.supplyAsync(() -> {
            return  new Person().getListPersons(query);
        });
        futureTablePerson.thenAcceptAsync(persons -> {
            Object[][] information = persons.stream()
                .map(person -> new Object[] { String.valueOf(person.getEmployeeId()),person.getTypeDniId(),person.getDocument(), person.getName(), person.getAddress(), person.getEmail() , person.getTelephone(), person.getReference()})
                .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.personList.makeTable(information,COLUMN_NAMES));

        });
    }

/*
    public void loadDataComboBoxAsync(){
        CompletableFuture<List<TypeDocument>> futurePerson = CompletableFuture.supplyAsync(new Person()::getListPersons);

        futurePerson.thenAcceptAsync(persons -> SwingUtilities.invokeLater(() -> {
            CustomComboBox<Person> comboBoxModel = new CustomComboBox<>(persons);
            panel.personEditor.cmbPerson.setModel((ComboBoxModel<Person>) comboBoxModel);
        }));
    }
*/
}
