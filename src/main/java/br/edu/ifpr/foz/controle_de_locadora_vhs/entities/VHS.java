package br.edu.ifpr.foz.controle_de_locadora_vhs.entities;

import java.time.LocalDate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class VHS {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Título deve ter ao menos um caracter")
    private String title;

    private String image;
    
    @NotBlank(message = "Diretor deve ter ao menos um caracter")
    private String director;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = true)
    @ToString.Exclude
    private Genre genre;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registrationDate;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private Status status;
}
