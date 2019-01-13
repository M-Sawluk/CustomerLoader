package domain.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "person")
public class PersonModel {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "surname")
    private String surname;
    @XmlElement(name = "age")
    private Integer age;
    @XmlElement(name = "city")
    private String city;
    @XmlElementWrapper(name = "contacts")
    @XmlElements(value = {
            @XmlElement(name = "phone"),
            @XmlElement(name = "email"),
            @XmlElement(name = "icq"),
            @XmlElement(name = "jabber")
    })
    private List<String> contacts;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public List<String> getContacts() {
        return contacts;
    }
}


