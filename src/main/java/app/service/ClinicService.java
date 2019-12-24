package app.service;

import app.domain.*;
import app.persistence.DBClinicStore;
import app.persistence.InMemoryClinicStore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ClinicService {
    //CREATE
    //private InMemoryClinicStore clinicStore = InMemoryClinicStore.getInstance();
    private DBClinicStore clinicStore = new DBClinicStore();
    //READ
    public List<Department> getDepartments() {return  clinicStore.getDepartments();}
    public List<DepartmentHead> getDepartmentHeads() {return  clinicStore.getDepartmentHeads();}
    public List<Doctor> getDoctors() {return  clinicStore.getDoctors();}
    public List<KindOfService> getKindOfServices() {return  clinicStore.getKindOfServices();}
    public List<Patient> getPatients() {return  clinicStore.getPatients();}
    public List<Service> getServices() {return  clinicStore.getServices();}
    public List<ServiceRegistration> getServiceRegistrations() {return  clinicStore.getServiceRegistrations();}
    public List<WorkingTime> getWorkingTimes() {return  clinicStore.getWorkingTimes();}

    public Department getDepartment(int id) {return clinicStore.getDepartment(id);}

    //UPDATE
   /* public Department getDepartmentById(int id) {
        return clinicStore.getDepartmentById(id);
    }
    public DepartmentHead getDepartmentHeadById(int id) {
        return clinicStore.getDepartmentHeadById(id);
    }*/
    public void updateDepartment(Department department){clinicStore.updateDepartment(department);}
    public void updateDepartmentHead(DepartmentHead departmentHead) { clinicStore.updateDepartmentHead(departmentHead);
    }
    public void updateDoctor(Doctor doctor) { clinicStore.updateDoctor(doctor);
    }
    public void updateKindOfService(KindOfService kindOfService) { clinicStore.updateKindOfService(kindOfService);
    }
    public void updatePatient(Patient patient) { clinicStore.updatePatient(patient);
    }
    public void updateService(Service service) { clinicStore.updateService(service);
    }
    public void updateServiceRegistration(ServiceRegistration serviceRegistration) { clinicStore.updateServiceRegistration(serviceRegistration); }
    public void updateWorkingTime(WorkingTime workingTime) { clinicStore.updateWorkingTime(workingTime);
    }
    //DELETE
    public boolean removeDepartmentByID(long departmentID) {
        Department department = new Department(departmentID);
        return clinicStore.removeDepartment(department);
    }
    public boolean removeDepartmentHeadByID(long departmentHeadID) {
        DepartmentHead departmentHead = new DepartmentHead(departmentHeadID);
        return clinicStore.removeDepartmentHead(departmentHead);
    }

    public boolean removeDoctorByID(long doctorID) {
        Doctor doctor = new Doctor(doctorID);
        return clinicStore.removeDoctor(doctor);
    }

    public boolean removeKindOfServiceByID(long kindOfServiceID) {
        KindOfService kindOfService = new KindOfService(kindOfServiceID);
        return clinicStore.removeKindOfService(kindOfService);
    }

    public boolean removePatientByID(long patientID) {
        Patient patient = new Patient(patientID);
        return clinicStore.removePatient(patient);
    }

    public boolean removeServiceByID(long serviceID) {
        Service service = new Service(serviceID);
        return clinicStore.removeService(service);
    }
    public boolean removeServiceRegistrationByID(long serviceRegistrationID) {
        ServiceRegistration serviceRegistration = new ServiceRegistration(serviceRegistrationID);
        return clinicStore.removeServiceRegistration(serviceRegistration);
    }
    public boolean removeWorkingTimeByID(long workingTimeID) {
        WorkingTime workingTime = new WorkingTime(workingTimeID);
        return clinicStore.removeWorkingTime(workingTime);
    }


    public void createDepartment(String departmentName) {
        clinicStore.createDepartment(departmentName);
    }

    public void createDepartmentHead(String departmentHeadFIO, int departmentID) { clinicStore.createDepartmentHead(departmentHeadFIO, (long)departmentID);
    }

    public void createDoctor(String doctorFIO, int departmentID) { clinicStore.createDoctor(doctorFIO, departmentID);
    }
    public void createKindOfService(Long depID, String name){ clinicStore.createKindOfService(depID, name);}

    public void createPatient(String name, LocalDate dayOfBirth) { clinicStore.createPatient(name, dayOfBirth);
    }

    public void createService(String name, BigDecimal price, long kindOfServiceID) { clinicStore.createService(name, price, kindOfServiceID);
    }

    public void createServiceRegistraton(int departmentID, int doctorID, int workTimeID, int serviceID, int patientID, int departmentHeadID, Date date, String comment) {
        clinicStore.createServiceRegistration(departmentID, doctorID, workTimeID, serviceID, patientID, departmentHeadID, date, comment);
    }

    public void createWorkingTime(Date begindate, Date enddate) { clinicStore.createWorkingTime(begindate, enddate);
    }
}
