package lk.codelabs.springboot.samples.propertyorderexample.modal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code", "id", "name"})
public class Student {

    int order;

    private String name;
    private int id;
    private String code;


    public String getName() {
           System.out.println("name :" + ++order);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
         System.out.println("id :" + ++order);
        return id;
    }

    public void setId(int id) {
        if (name!=null && !name.equalsIgnoreCase("Krish")) {
            throw new RuntimeException("Error on name");
        }
        this.id = id;
    }

    public String getCode() {
         System.out.println("code :" + ++order);
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
