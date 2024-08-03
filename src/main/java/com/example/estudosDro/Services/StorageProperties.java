package com.example.estudosDro.Services;


import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration("storage")
@Data
public class StorageProperties {
    //storage temporario de videos, pode ser usando algo melhor como um bd ou uma aws de vida
    private String location = "C:\\Users\\guibe\\OneDrive\\Área de Trabalho\\java";
}
