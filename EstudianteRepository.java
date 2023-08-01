import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Aquí puedes definir consultas personalizadas si las necesitas
}
