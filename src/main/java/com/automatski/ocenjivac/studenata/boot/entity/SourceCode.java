package com.automatski.ocenjivac.studenata.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceCode {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Lob
    @Column
    private String sourceCode;

    @OneToOne
    @JoinColumn(name = "file_id")
    private Files files; // variable name('files') needs to match the mappedBy value

    @OneToOne
    @JoinColumn(name = "template_id")
    private SourceCode templateId; // variable name('files') needs to match the mappedBy value

    public SourceCode(String sourceCode, Files files) {
        this.sourceCode = sourceCode;
        this.files = files;
    }

    @OneToMany(mappedBy = "sourceCode",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Method> method;
}
