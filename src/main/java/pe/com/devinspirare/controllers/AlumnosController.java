package pe.com.devinspirare.controllers;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import pe.com.devinspirare.dtos.AlumnoDTO;
import pe.com.devinspirare.services.AlumnoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashSet;
import java.util.Set;

@Path("/alumnos")
@ApplicationScoped
@Slf4j
public class AlumnosController {

    @Inject
    AlumnoService alumnoService;

    @GET
    public Set<AlumnoDTO> getAlumnos() throws Exception {
        try {
            return new HashSet<>(alumnoService.allStudents());
        } catch (CircuitBreakerOpenException circuitBreakerOpenException) {
            log.info("Se abrio el circuito por aqui.");
            throw circuitBreakerOpenException;
        } catch (Exception e) {
            log.info("Se deriva el error nada más");
            throw e;
        }
    }
}
