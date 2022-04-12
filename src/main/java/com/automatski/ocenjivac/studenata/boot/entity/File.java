package com.automatski.ocenjivac.studenata.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "uuid", strategy = "uuid1")
    private String id;

    private String fileName;
    private String fileType;
    private String grade;

    @Lob
    private byte[] data;

    public File(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    @OneToOne(mappedBy = "file")
    private Student student;

}
