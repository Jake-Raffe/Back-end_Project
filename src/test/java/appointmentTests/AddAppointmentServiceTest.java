package appointmentTests;
import com.bnta.Appointment.Appointment;
import com.bnta.Appointment.AppointmentDAO;
import com.bnta.Appointment.AppointmentService;
import com.bnta.patient.BloodType;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class AddAppointmentServiceTest {

    @Mock private AppointmentDAO appointmentDAO;
    private AppointmentService underTest;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AppointmentService(appointmentDAO);
    }
    //add Appointment
    @Test
    void successfulAddAppointment(){
        //Given
        Appointment testAppointment = new Appointment(1,
                2,
                3,

                LocalDate.of(2022,Month.JUNE, 12),
                LocalTime.of(14, 23));


        Mockito.when(appointmentDAO.bookAppointment(eq(testAppointment))).thenReturn(1);
        Mockito.when(appointmentDAO.viewAppointment()).thenReturn(List.of(new Appointment(1,
                2,
                3,
                LocalDate.of(2022,Month.JUNE, 12),
                LocalTime.of(14, 23))));



        //When
        int result = underTest.bookAppointment(testAppointment);
        ArgumentCaptor<Appointment> appointmentArgumentCaptor = ArgumentCaptor.forClass(Appointment.class);
        Mockito.verify(appointmentDAO).bookAppointment(appointmentArgumentCaptor.capture());
        Appointment expectedAppointment = appointmentArgumentCaptor.getValue();


        //Then
        assertThat(expectedAppointment).isEqualTo(testAppointment);
        assertThat(result).isEqualTo(1);

    }
}
