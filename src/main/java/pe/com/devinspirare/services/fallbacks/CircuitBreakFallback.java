package pe.com.devinspirare.services.fallbacks;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.metrics.annotation.Counted;
import pe.com.devinspirare.dtos.AlumnoDTO;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class CircuitBreakFallback implements FallbackHandler<List<AlumnoDTO>> {

    @Counted(name = "howManyExecuteCallback", description = "Cuantas veces se ejecuto el fallback de Alumno All Students")
    @Override
    public List<AlumnoDTO> handle(ExecutionContext context) {
        if (context.getFailure() instanceof CircuitBreakerOpenException) {
            log.info("CircuitBreaker is Open");
        }
        log.info("EJECUTADO DESDE FALLBACK CLASE");
        AlumnoDTO alumnoDTO2 = AlumnoDTO.builder()
                .codigo("ALUMNO CON CODIGIO FALLBACK").build();
        return Arrays.asList(alumnoDTO2);
    }
}
