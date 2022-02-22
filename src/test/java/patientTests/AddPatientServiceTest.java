package patientTests;

import com.bnta.patient.PatientDAO;
import com.bnta.exception.IllegalStateException;
import com.bnta.patient.BloodType;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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


    @Test
    void addNullPatient(){
        // Given
        Patient examplePatient = new Patient();
        Mockito.when(patientDAO.insertPatient(eq(examplePatient))).thenThrow(IllegalStateException.class);
        IllegalStateException badPatient = new IllegalStateException("Unable to create patient");

        // When
        int result = underTest.addNewPatient(examplePatient);
        ArgumentCaptor<IllegalStateException> exceptionArgumentCaptor = ArgumentCaptor.forClass(IllegalStateException.class);
        IllegalStateException expectedException = exceptionArgumentCaptor.getValue();

        // Then
        assertThat(expectedException).isEqualTo(badPatient);
        assertThat(result).isNotEqualTo(1);
    }

    @Test
    void addWrongBodyPatient(){}
}
