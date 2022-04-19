package com.automatski.ocenjivac.studenata.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String teacher;

    @OneToMany(mappedBy = "template",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Method> method;

    @OneToOne(mappedBy = "templateId",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private SourceCode sourceCodeId;
}
