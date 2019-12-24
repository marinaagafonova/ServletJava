package app.persistence;

import app.domain.*;

import java.util.Date;
import java.util.List;

public interface ClinicStore {
    List<Department> getDepartments();
    List<DepartmentHead> getDepartmentHeads();
    List<Doctor> getDoctors();
    List<KindOfService> getKindOfServices();
    List<Patient> getPatients();
    List<Service> getServices();
    List<ServiceRegistration> getServiceRegistrations();
    List<WorkingTime> getWorkingTimes();

    void updateDepartment(Department department);
    void updateDepartmentHead(DepartmentHead departmentHead);
    void updateDoctor(Doctor doctor);
    void updateKindOfService(KindOfService kindOfService);
    void updatePatient(Patient patient);
    void updateService(Service service);
    void updateServiceRegistration(ServiceRegistration serviceRegistration);
    void updateWorkingTime(WorkingTime workingTime);

    boolean removeDepartment(Department department);
    boolean removeDepartmentHead(DepartmentHead departmentHead);
    boolean removeDoctor(Doctor doctor);
    boolean removeKindOfService(KindOfService kindOfService);
    boolean removePatient(Patient patient);
    boolean removeService(Service service);
    boolean removeServiceRegistration(ServiceRegistration serviceRegistration);
    boolean removeWorkingTime(WorkingTime workingTime);
}
