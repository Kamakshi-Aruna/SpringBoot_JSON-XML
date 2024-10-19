package org.example.springboot_api.model;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "users")  // MongoDB collection name
@JacksonXmlRootElement(localName = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private int age;
}
