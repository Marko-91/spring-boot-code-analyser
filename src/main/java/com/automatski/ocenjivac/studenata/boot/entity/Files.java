package com.automatski.ocenjivac.studenata.boot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String fileType;
    private String grade;

    @Lob
    private byte[] data;

    public Files(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Files(String fileName, String fileType, String grade, byte[] data, Student student) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.grade = grade;
        this.data = data;
        this.student = student;
    }
}
