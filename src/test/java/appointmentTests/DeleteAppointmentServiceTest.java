package appointmentTests;

import com.bnta.Appointment.AppointmentDAO;
import com.bnta.Appointment.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeleteAppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;
    private AppointmentService underTest;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AppointmentService(appointmentDAO);
    }
}
