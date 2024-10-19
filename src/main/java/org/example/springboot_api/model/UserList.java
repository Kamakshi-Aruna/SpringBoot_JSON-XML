package org.example.springboot_api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "userList")
public class UserList {

    @JacksonXmlElementWrapper(useWrapping = false)  // Avoid wrapping with additional element
    @JacksonXmlProperty(localName = "user")  // Child element for each user
    private List<User> users;

    public UserList(List<User> users) {
        this.users = users;
    }
}