package controllers.person;

import views.person.PersonView;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PersonController {

    final private PersonView panel;

    private int employee_id=0;
    final String[] COLUMN_NAMES = { "Id","DNI","Telefono","Nombre","Direccion","Email","Referencias"};

    public PersonController(PersonView panel) { this.panel = panel; }

    public void renderObjects() { this.panel.personList.makeTableHeader(COLUMN_NAMES);}

    public void loadDataTableAsync(String query){
       // CompletableFuture<List<Person>>
    }
}
