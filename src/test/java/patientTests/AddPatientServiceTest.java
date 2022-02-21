package patientTests;

import com.bnta.DAOs.PatientDAO;
import com.bnta.DAOs.PatientDBAccess;
import com.bnta.patient.BloodType;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import jdk.jfr.SettingDefinition;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.mockito.Mockito.*;

public class AddPatientServiceTest {

    @Mock private PatientDAO patientDAO;
    private PatientService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new PatientService(patientDAO);
    }

    @Test
    void successfulAddPatient(){
        // Given
        Patient examplePatient =
                new Patient(2,
                "John",
                "07910975166",
                "johndc@gmail.com",
                BloodType.B);

        Mockito.when(patientDAO.insertPatient(eq(examplePatient))).thenReturn(1);
        Mockito.when(patientDAO.selectAllPatients()).thenReturn(List.of(new Patient(2,
                "John",
                "07910975166",
                "johndc@gmail.com",
                BloodType.B)));

        // When
        int result = underTest.addNewPatient(examplePatient);
        ArgumentCaptor<Patient> patientArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        Mockito.verify(patientDAO).insertPatient(patientArgumentCaptor.capture());
        Patient expectedPatient = patientArgumentCaptor.getValue();

        // Then
        assertThat(expectedPatient).isEqualTo(examplePatient);
        assertThat(result).isEqualTo(1);
    }

}
