package com.stackroute.contentservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Content {


    @Id
    int id;
    String title;
    String subTitle;
    String description;
    int  authorId;
    List<Integer>  editorIds; // list of userId of editors
    List<Integer> designerIds; // list of userId of designer
    String typeName;
    List<String> genres;
    List<String> targetedAudience;
    String  createdAt;
    String gitUrl;


}
