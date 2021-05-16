package com.francis.newfacebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;


    private String commentBody;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Post post;

//    @ManyToOne
//    private Post post;
}
