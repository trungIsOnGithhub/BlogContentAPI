package com.api.forum.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
	name = "role",
	uniqueConstraint = @UniqueConstraint(
		name = "RoleLevelDivision",
		columnNames = {"name", "level"}
	)
)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    // create directly in sql schema
    @Column(columnDefinition = "INTEGER DEFAULT 0 CHECK level >= 1")
    private Integer level;
}