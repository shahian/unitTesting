package com.shahian.unittesting.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Group(String title, String description) {
        this.title = title;
        this.description = description;
    }
}

