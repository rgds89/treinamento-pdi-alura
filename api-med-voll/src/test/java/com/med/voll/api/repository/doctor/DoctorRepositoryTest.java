package com.med.voll.api.repository.doctor;

import com.med.voll.api.enums.Specialties;
import com.med.voll.api.model.address.Address;
import com.med.voll.api.model.consulation.Consulation;
import com.med.voll.api.model.doctor.Doctor;
import com.med.voll.api.model.patient.Patient;
import com.med.voll.api.model.telephone.Telephone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria retornar null quando o único médico cadastrado não estiver disponível na data")
    void isDoctorAvailableSceneOne() {
        var date = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = registerDoctor("Medico", "medico@voll.med", 123456L, Specialties.CARDIOLOGIA);
        var patient = registerPatient("Paciente", "patient@email.com", "00000000000");
        cadastrarConsulta(doctor, patient, date);
        var doctorFree = doctorRepository.findBySpecialtiesAndData(Specialties.CARDIOLOGIA, date).orElse(null);
        assertThat(doctorFree).isNull();
    }

    private void cadastrarConsulta(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Consulation(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String nome, String email, Long crm, Specialties specialties) {
        var doctor = DoctorData(nome, email, crm, specialties);
        em.persist(doctor);
        registerTelephone(doctor);
        return doctor;
    }

    private Patient registerPatient(String nome, String email, String cpf) {
        var patient = patientData(nome, email, cpf);
        em.persist(patient);
        return patient;
    }

    private Address registerAddress(){
        var address = AddressData();
        em.persist(address);
        return address;
    }

    private void registerTelephone(Doctor  doctor){
        var telephone = TelephoneData();
        telephone.setDoctor(doctor);
        em.persist(telephone);
    }

    private Doctor DoctorData(String nome, String email, Long crm, Specialties specialties) {
        return new Doctor(null,
                nome,
                email,
                crm,
                specialties,
                registerAddress(),
                null,
                true
        );
    }

    private Patient patientData(String nome, String email, String cpf) {
        return new Patient(null,
                nome,
                email,
                cpf,
                "61999999999",
                true,
                registerAddress()
        );
    }

    private Address AddressData() {
        return new Address(null,
                "rua xpto",
                "bairro",
                "00000000",
                null,
                0L,
                "Brasilia",
                "BF"
        );
    }

    private Telephone TelephoneData() {
        return new Telephone(null, "61", "999999999", null);
    }
}