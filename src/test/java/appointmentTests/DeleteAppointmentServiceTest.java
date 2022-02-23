package appointmentTests;

import com.bnta.appointment.AppointmentDAO;
import com.bnta.appointment.AppointmentService;
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

    //Delete appointment


//    public int removePerson(int id) {
//        boolean exists = doesPersonWithIdExists(id);
//        if (!exists) {
//            throw new IllegalStateException("person with id " + id + " not found");
//        }
//        return personDAO.deletePerson(id);
//    }






}
