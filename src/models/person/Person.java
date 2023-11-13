package models.person;


import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person  {

    private Integer employee_id;
    private String type_dni_id;
    private String document;
    private String telephone;
    private String name;
    private String address;
    private String reference;
    private String email;

    public Person() {}
        public Integer getEmployeeId () {
            return this.employee_id;
        }
        public String getTypeDniId () {
            return this.type_dni_id;
        }
        public String getDocument(){
            return this.document;
        }
        public String getTelephone() {
            return this.telephone;
        }
        public String getName() {
            return this.name;
        }
        public String getAddress() {
            return this.address;
        }
        public String getReference() {
            return this.reference;
        }
        public String getEmail() {
            return this.email;
        }

        public void setEmployeeId (Integer employee_id){
            this.employee_id = employee_id;
        }
        public void setTypeDniId(String type_dni_id){
            this.type_dni_id = type_dni_id;
        }
        public void setDocument(String document){ this.document = document;}
        public void setTelephone(String telephone){
            this.telephone = telephone;
        }
        public void setName(String name ){
            this.name = name;
        }
        public void setAddress(String address){
            this.address= address;
        }
        public void setReference(String reference){
            this.reference = reference;
        }
        public void setEmail(String email){
            this.email = email;
        }

        public List<Person> getListPersons(String query){
            try {
                String sql = query.trim().isEmpty()? ModelSQL.SQL_STMT_GET_EMPLOYEE:ModelSQL.SQL_STMT_GET_EMPLOYEE_BY_LIKE;
                ResultSet result;
                if(query.trim().isEmpty()) {
                    result = Db.executeQuery(sql);
                }else{
                    query = "%"+query+"%";
                    result = Db.executeQuery(sql, Arrays.asList(
                            new Parameter<>(1,query),
                            new Parameter<>(2,query),
                            new Parameter<>(3,query),
                            new Parameter<>(4,query)
                    ));
                }

                List<Person> personList = new ArrayList<>();
                while (result.next()){
                    var person = new Person();
                    person.setEmployeeId(result.getInt("employee_id"));
                    person.setTypeDniId(result.getString("document_type_id"));
                    person.setDocument(result.getString("document"));
                    person.setName(result.getString("full_name"));
                    person.setAddress(result.getString("address"));
                    person.setEmail(result.getString("email"));
                    person.setTelephone(result.getString("telephone_number"));
                    person.setReference(result.getString("reference"));
                    personList.add(person);
                }
                result.getStatement().close();
                result.close();
                return personList;
            }catch (Exception e){
                return new ArrayList<>();
            }
        }

        public Person getPerson(int employee_id){
        try{
            var result = Db.executeQuery(ModelSQL.SQL_STMT_GET_EMPLOYEE, List.of(
                    new Parameter<>(1, employee_id)
            ));
            Person person = new Person();
            while (result.next()){
                person.setEmployeeId(result.getInt("employee_id"));
                person.setTypeDniId(result.getString("document_type_id"));
                person.setDocument(result.getString("document"));
                person.setName(result.getString("full_name"));
                person.setAddress(result.getString("address"));
                person.setEmail(result.getString("email"));
                person.setTelephone(result.getString("telephone_number"));
                person.setReference(result.getString("reference"));
            }
            return person;
        }catch (Exception e){
            return new Person();
        }
    }

    /*
    public int save(){
        try{
            String query = this.employee_id==0? ModelSQL.
        }
    }
*/



}