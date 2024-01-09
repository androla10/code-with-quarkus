package pe.com.devinspirare.dtos;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class PersonaDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
}
