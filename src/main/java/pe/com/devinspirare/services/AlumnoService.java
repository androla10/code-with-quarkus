package pe.com.devinspirare.services;

import io.smallrye.faulttolerance.api.CircuitBreakerName;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import pe.com.devinspirare.dtos.AlumnoDTO;
import pe.com.devinspirare.services.fallbacks.CircuitBreakFallback;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Slf4j
public class AlumnoService {

    private AtomicLong counter = new AtomicLong(0);

    @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
    @Timed(name = "checksTimer", description = "A measure of how long it takes to perform the primality test.", unit = MetricUnits.MILLISECONDS)
    @CircuitBreaker(failureRatio = 0.5, requestVolumeThreshold = 4, delay = 10000)
    @CircuitBreakerName(value = "CircuitBreakerAllStudents")
    //@Fallback(CircuitBreakFallback.class)
    public List<AlumnoDTO> allStudents() throws Exception {
        possibleFail();
        AlumnoDTO alumnoDTO = AlumnoDTO.builder()
                .codigo("U201825572").build();
        AlumnoDTO alumnoDTO2 = AlumnoDTO.builder()
                .codigo("U201825573").build();
        return Arrays.asList(alumnoDTO, alumnoDTO2);
    }

    public List<AlumnoDTO> Metodo1() {
        log.info("EJECUTADO DESDE FALLBACK");
        AlumnoDTO alumnoDTO2 = AlumnoDTO.builder()
                .codigo("ALUMNO CON CODIGIO FALLBACK").build();
        return Arrays.asList(alumnoDTO2);
    }

    private void possibleFail() {
        final Long invocationNumber = counter.getAndIncrement();
        if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
            throw new RuntimeException("Service failed.");
        }
    }


}
