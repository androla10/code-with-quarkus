package pe.com.devinspirare.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlumnoDTO extends PersonaDTO {
    private String codigo;
    private LocalDate fechaIngreso;
}
