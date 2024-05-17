package com.example.Securityprueba.entities.candidatesModels;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@MappedSuperclass
@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class Candidates implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
    private String name;


    private String lastName;
    @Column(unique = true)
    private Long identification;
    private Integer grade;
    private String namePhoto;

    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")

    private byte[] data;
@Column(name = "`group`")
    private String group;

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
        this.identification = identification;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    public String getGroup() {
        return group;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
