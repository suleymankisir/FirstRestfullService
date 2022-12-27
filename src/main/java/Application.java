import jakarta.ws.rs.ApplicationPath;

// bu sınıf jakarta 'ya özel
@ApplicationPath("/")
public class Application extends jakarta.ws.rs.core.Application
{
    // başka bir jax-rs teknolojisnde mesela resteasy mesela jersey mesela spring rest gibi
    // farklı bir konfigürasyon olur
}