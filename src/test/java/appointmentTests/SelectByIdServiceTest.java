package appointmentTests;

import com.bnta.appointment.AppointmentDAO;
import com.bnta.appointment.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SelectByIdServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;
    private AppointmentService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AppointmentService(appointmentDAO);


    }
}
