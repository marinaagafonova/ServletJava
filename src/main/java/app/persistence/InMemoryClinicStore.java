package app.persistence;

import app.domain.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.time.LocalDate;

public class InMemoryClinicStore implements ClinicStore {
    private static InMemoryClinicStore instance;



    private List<Department> departments = new ArrayList<>();
    private List<DepartmentHead> departmentHeads = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<KindOfService> kindOfServices = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Service> services = new ArrayList<>();
    private List<ServiceRegistration> serviceRegistrations = new ArrayList<>();
    private List<WorkingTime> workingTimes = new ArrayList<>();

    //private Updater
    protected static <T extends Item> int getIndexOfItem(List<T> list, T certain) {
        //int index = -1;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == certain.getId())
                return i;
        }
        return -1;
    }

    private InMemoryClinicStore() {
        populateDepartments();
        populateDepartmentHeads();

        populateDoctors();
        populateServices();
        populateKindOfService();
        populatePatients();
        populateWorkingTime();
        populateServiceRegistration();
    }

    public static InMemoryClinicStore getInstance() {
        if(instance == null) {
            instance = new InMemoryClinicStore();
        }
        return instance;
    }

    //CREATE

    private void populateDepartments() {
        //filling
        List<Department> newDepartments = Arrays.asList(new Department((long)1, "Офмальмология"),
                new Department((long)2, "Кардиология"),
                new Department((long)3, "Неврология"));
        departments.addAll(newDepartments);
    }
    private void populateDepartmentHeads() {
        //filling
        List<DepartmentHead> newPersons = Arrays.asList(new DepartmentHead((long)1, "Иванов Иван Иванович", departments.get(0).getId()),
                new DepartmentHead((long)2, "Борисов Борис Борисович", departments.get(1).getId()),
                new DepartmentHead((long)3, "Фёдоров Фёдор Фёдорович", departments.get(2).getId()));
        departmentHeads.addAll(newPersons);
    }
    private void populateDoctors() {
        //filling
        List<Doctor> newDoctors = Arrays.asList(new Doctor((long)1, "Петров Пётр Петрович", departments.get(0).getId()),
                new Doctor((long)2, "Афанасьев Афанасий Афанасиевич", departments.get(1).getId()),
                new Doctor((long)3, "Федотов Федот Федотович", departments.get(2).getId()));
        doctors.addAll(newDoctors);
    }
    private void populateKindOfService() {
        //filling
        List<KindOfService> newKindsOfServices = Arrays.asList(new KindOfService((long)1, departments.get(0).getId(), "Обследование"),
                new KindOfService((long)2, departments.get(1).getId(), "Анализ"),
                new KindOfService((long)3, departments.get(2).getId(),"Консультация"));
        kindOfServices.addAll(newKindsOfServices);
    }
    private void populatePatients() {
        //filling
        List<Patient> newPatients = Arrays.asList(new Patient((long)1, "Рогова Гаянэ Тимуровна", LocalDate.of(1960, Month.FEBRUARY, 12)),
                new Patient((long)2, "Ларионова Эрика Юлиановна", LocalDate.of(1970, Month.MARCH, 7)),
                        new Patient((long)3, "Говачёв Михаил Борисович", LocalDate.of(1955, Month.APRIL, 20)));
        patients.addAll(newPatients);
    }
    private void populateServices() {
        //filling
        List<Service> newServices = Arrays.asList(new Service((long)1, "Консультация первичная", new BigDecimal("500.00"), kindOfServices.get(2).getId()),
                new Service((long)2, "Консультация вторичная", new BigDecimal("600.00"), kindOfServices.get(0).getId()),
                new Service((long)3, "Забор крови из вены", new BigDecimal("300.00"), kindOfServices.get(1).getId()));
        services.addAll(newServices);
    }

    private Date createDate(int year, int month, int day, int hour, int munite) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,munite);

        return cal.getTime();
    }

    private void populateWorkingTime() {
        List<WorkingTime> newWorkingTime = Arrays.asList(new WorkingTime((long)1, createDate(2019, 10, 18, 14, 0), createDate(2019, 10, 18, 14,30)),
                                                        new WorkingTime((long)2, createDate(2019, 10, 18, 14, 35), createDate(2019, 10, 18, 15,05)),
                                                        new WorkingTime((long)3, createDate(2019, 10, 18, 15, 10), createDate(2019, 10, 18, 15,40)));
        workingTimes.addAll(newWorkingTime);
    }
    private void populateServiceRegistration() {
        List<ServiceRegistration> newServiceRegistration = Arrays.asList(new ServiceRegistration((long)1, departments.get(0).getId(), doctors.get(0).getId(), workingTimes.get(0).getId(), services.get(0).getId(), patients.get(0).getId(), departmentHeads.get(0).getId(), createDate(2019, 10, 17, 11, 00), ""),
                new ServiceRegistration((long)2, departments.get(1).getId(), doctors.get(1).getId(), workingTimes.get(1).getId(), services.get(1).getId(), patients.get(1).getId(), departmentHeads.get(1).getId(), createDate(2019, 10, 17, 11, 29), ""),
                new ServiceRegistration((long)3, departments.get(2).getId(), doctors.get(2).getId(), workingTimes.get(2).getId(), services.get(2).getId(), patients.get(2).getId(), departmentHeads.get(2).getId(), createDate(2019, 10, 17, 12, 00), ""));
        serviceRegistrations.addAll(newServiceRegistration);
    }

    //READ



    public List<Department> getDepartments() {
        return departments;
    }

    public List<DepartmentHead> getDepartmentHeads() {
        return departmentHeads;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<KindOfService> getKindOfServices() {
        return kindOfServices;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<ServiceRegistration> getServiceRegistrations() {
        return serviceRegistrations;
    }

    public List<WorkingTime> getWorkingTimes() {
        return workingTimes;
    }


    //UPDATE
    /*public Department getDepartmentById(int id) {
        Department founded = new Department(id);
        for(Department department : departments) {
            if(department.getId() == id)
                founded.setName(department.getName());
        }
        return founded;
    }
    public DepartmentHead getDepartmentHeadById(int id) {
        DepartmentHead founded = new DepartmentHead(id);
        for(DepartmentHead departmentHead : departmentHeads) {
            if(departmentHead.getId() == id){
                founded.setFio(departmentHead.getFio());
                founded.setId_department(departmentHead.getId_department());
            }
        }
        return founded;
    }
*/

    public void updateDepartment(Department department) {  departments.set(getIndexOfItem(departments, department), department);}
    public void updateDepartmentHead(DepartmentHead departmentHead) {  departmentHeads.set(getIndexOfItem(departmentHeads,departmentHead), departmentHead);}
    public void updateDoctor(Doctor doctor) { doctors.set(getIndexOfItem(doctors, doctor), doctor);  }
    public void updateKindOfService(KindOfService kindOfService) { kindOfServices.set(getIndexOfItem(kindOfServices, kindOfService), kindOfService);}
    public void updatePatient(Patient patient) { patients.set(getIndexOfItem(patients, patient), patient); }
    public void updateService(Service service) { services.set(getIndexOfItem(services, service), service); }
    public void updateServiceRegistration(ServiceRegistration serviceRegistration) { serviceRegistrations.set(getIndexOfItem(serviceRegistrations, serviceRegistration), serviceRegistration); }
    public void updateWorkingTime(WorkingTime workingTime) { workingTimes.set(getIndexOfItem(workingTimes, workingTime), workingTime);
    }
    //DELETE
    public boolean removeDepartment(Department department) {
        return departments.remove(department);
    }
    public boolean removeDepartmentHead(DepartmentHead departmentHead) {
        return departmentHeads.remove(departmentHead);
    }
    public boolean removeDoctor(Doctor doctor) {
        return doctors.remove(doctor);
    }
    public boolean removeKindOfService(KindOfService kindOfService) {
        return kindOfServices.remove(kindOfService);
    }
    public boolean removePatient(Patient patient) {
        return patients.remove(patient);
    }
    public boolean removeService(Service service) {
        return services.remove(service);
    }
    public boolean removeServiceRegistration(ServiceRegistration serviceRegistration) {
        return serviceRegistrations.remove(serviceRegistration);
    }
    public boolean removeWorkingTime(WorkingTime workingTime) {
        return workingTimes.remove(workingTime);
    }

}
