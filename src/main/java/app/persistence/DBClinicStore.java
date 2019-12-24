package app.persistence;

import app.domain.*;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBClinicStore implements ClinicStore{

    interface Mapper<R> {
        List<R> foo(ResultSet param) throws  SQLException;
    }

    private static final String URL = "jdbc:h2:./clinic";
    private static final String USER = "nobody";
    private static final String PASSWORD = "111";

    public DBClinicStore() {
        try {
            Class.forName("org.h2.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver class wasn't found" + e.getMessage());
        }
        //createTables();
    }

    private void createTables() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE departments (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "name VARCHAR (100) NOT NULL" + ")");

            statement.execute("INSERT INTO departments" + "(name)" + "VALUES ('Офмальмология')");

            statement.execute("CREATE TABLE departmentheads (" + "id INT NOT NULL PRIMARY KEY auto_increment, "
                                                                + "fio VARCHAR (100) NOT NULL,"
                                                                + "id_department INT, foreign key (id_department) references departments(id) ON DELETE CASCADE" + ")");

            statement.execute("INSERT INTO departmentheads" + "(fio, id_department)" + "VALUES ('Иванов Иван Иванович', 1)");

            statement.execute("CREATE TABLE doctors (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "fio VARCHAR (100) NOT NULL,"
                    + "id_department INT, foreign key (id_department) references departments(id) ON DELETE CASCADE" + ")");

            statement.execute("INSERT INTO doctors" + "(fio, id_department)" + "VALUES ('Петров Пётр Петрович', 1)");

            statement.execute("CREATE TABLE kindsofservice (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "name VARCHAR (100) NOT NULL,"
                    + "id_department INT, foreign key (id_department) references departments(id) ON DELETE CASCADE"
                    + ")");

            statement.execute("INSERT INTO kindsofservice" + "(name, id_department)" + "VALUES ('Обследование', 1)");

            statement.execute("CREATE TABLE services (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "name VARCHAR (100) NOT NULL," + "price DECIMAL NOT NULL, " + "id_kind INT, foreign key (id_kind) references kindsofservice(id) ON DELETE CASCADE" +")");
            statement.execute("INSERT INTO services" + "(name, price, id_kind)" + "VALUES ('Консультация первичная', 500.0, 1)");


            statement.execute("CREATE TABLE patients (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "fio VARCHAR (100) NOT NULL,"
                    + "dayOfBirth TIMESTAMP "+ ")");
            statement.execute("INSERT INTO patients" + "(fio, dayOfBirth)" + "VALUES ('Рогова Гаянэ Тимуровна', {ts '1960-02-12 0:0:0.69'})");

            statement.execute("CREATE TABLE workingtimes (" + "id INT NOT NULL PRIMARY KEY auto_increment, "
                                                        + "beginDate TIMESTAMP , "
                                                        + "endDate TIMESTAMP "+  ")");

            statement.execute("INSERT INTO workingtimes" + "(beginDate, endDate)" + "VALUES ({ts '2019-12-17 15:30:0.69'}, {ts '2019-12-17 16:00:0.69'})");

            statement.execute("CREATE TABLE serviceregistrations (" + "id INT NOT NULL PRIMARY KEY auto_increment, "
                                                                    + "dateOfRegistration TIMESTAMP, "
                                                                    + "comment VARCHAR (10000), "
                                                                    + "id_department INT, foreign key (id_department) references departments(id) ON DELETE CASCADE,"
                    + "id_doctor INT, foreign key (id_doctor) references doctors(id) ON DELETE CASCADE,"
                    + "id_workingTime INT, foreign key (id_workingTime) references workingtimes(id) ON DELETE CASCADE,"
                    + "id_service INT, foreign key (id_service) references services(id) ON DELETE CASCADE,"
                    + "id_patient INT, foreign key (id_patient) references patients(id) ON DELETE CASCADE,"
                    + "id_departmentHead INT, foreign key (id_departmentHead) references departmentheads(id) ON DELETE CASCADE"
                    +  ")");
        }
        catch (SQLException e) {
            System.out.println("DDL or DML statement error" + e.getMessage());
        }
    }

    protected <T extends Item> T getItemById(Long id, String table) {
        Mapper a = new Mapper<T>() { //a = mapper
            @Override
            public List<T> foo(ResultSet resultSet) {
                return null;
            }
        };
        List<T> categories = executeSelectQuery(String.format("select * from %s where id=%d", table, id), a);
        if(categories != null && !categories.isEmpty())
        {
            return categories.get(0);
        }
        return null;
    }

    //попробовать переделать в универ методом с листом парам (шесть методов, getObjById)
    @Override
    public List<Department> getDepartments() {
        Mapper a = (Mapper<Department>) resultSet -> {
            try {
                List<Department> departments = new ArrayList<>();
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    Department department = new Department(id, name);
                    departments.add(department);
                }
                return departments;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return null;
        };
        return executeSelectQuery("select * from departments", a);
    }

    public Department getDepartment(int id) {
        Mapper a = (Mapper<Department>) resultSet -> {
            try {
                List<Department> departments = new ArrayList<>();
                while (resultSet.next()) {
                    Long iD = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    Department department = new Department(iD, name);
                    departments.add(department);
                }
                return departments;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return null;
        };
        List<Department> departments = executeSelectQuery(String.format("select * from departments where id=%d", id), a);
        return departments.get(0);
    }

    @Override
    public List<DepartmentHead> getDepartmentHeads() {
        Mapper a = new Mapper<DepartmentHead>() {
            @Override
            public List<DepartmentHead> foo(ResultSet resultSet) {
                try {
                    List<DepartmentHead> departmentHeads = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        String fio = resultSet.getString("fio");
                        Long id_department = resultSet.getLong("id_department");
                        DepartmentHead departmentHead = new DepartmentHead(id, fio, id_department);
                        departmentHeads.add(departmentHead);
                    }
                    return departmentHeads;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from departmentheads", a);
    }

    @Override
    public List<Doctor> getDoctors() {
        Mapper a = new Mapper<Doctor>() {
            @Override
            public List<Doctor> foo(ResultSet resultSet) {
                try {
                    List<Doctor> doctors = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        String fio = resultSet.getString("fio");
                        Long id_department = resultSet.getLong("id_department");
                        Doctor doctor = new Doctor(id, fio, id_department);
                        doctors.add(doctor);
                    }
                    return doctors;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from doctors", a);
    }

    @Override
    public List<KindOfService> getKindOfServices() {
        Mapper a = new Mapper<KindOfService>() {
            @Override
            public List<KindOfService> foo(ResultSet resultSet) {
                try {
                    List<KindOfService> kindsOfServices = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        Long id_department = resultSet.getLong("id_department");

                        KindOfService kindOfService = new KindOfService(id, id_department, name);
                        kindsOfServices.add(kindOfService);
                    }
                    return kindsOfServices;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from kindsofservice", a);
    }

    @Override
    public List<Patient> getPatients() {
        Mapper a = new Mapper<Patient>() {
            @Override
            public List<Patient> foo(ResultSet resultSet) {
                try {
                    List<Patient> patients = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        String fio = resultSet.getString("fio");
                        Date dob = resultSet.getDate("dayOfBirth");
                        Instant instant = Instant.ofEpochMilli(dob.getTime());
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        LocalDate dayOfBirth = localDateTime.toLocalDate();
                        Patient patient = new Patient(id, fio, dayOfBirth);
                        patients.add(patient);
                    }
                    return patients;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from patients", a);
    }

    @Override
    public List<Service> getServices() {
        Mapper a = new Mapper<Service>() {
            @Override
            public List<Service> foo(ResultSet resultSet) {
                try {
                    List<Service> services = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        BigDecimal price = resultSet.getBigDecimal("price");
                        Long id_kind = resultSet.getLong("id_kind");
                        Service service = new Service(id, name, price, id_kind);
                        services.add(service);
                    }
                    return services;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from services", a);
    }

    @Override
    public List<ServiceRegistration> getServiceRegistrations() {
        Mapper a = new Mapper<ServiceRegistration>() {
            @Override
            public List<ServiceRegistration> foo(ResultSet resultSet) {
                try {
                    List<ServiceRegistration> serviceRegistrations = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        Date dateOfRegistration = resultSet.getTimestamp("dateOfRegistration");
                        String comment = resultSet.getString("comment");
                        Long id_department = resultSet.getLong("id_department");
                        Long id_doctor = resultSet.getLong("id_doctor");
                        Long id_workingTime = resultSet.getLong("id_workingTime");
                        Long id_service = resultSet.getLong("id_service");
                        Long id_patient = resultSet.getLong("id_patient");
                        Long id_departmentHead = resultSet.getLong("id_departmentHead");
                        ServiceRegistration serviceRegistration = new ServiceRegistration(id, id_department, id_doctor, id_workingTime, id_service, id_patient, id_departmentHead, dateOfRegistration, comment);
                        serviceRegistrations.add(serviceRegistration);
                    }
                    return serviceRegistrations;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from serviceregistrations", a);
    }

    @Override
    public List<WorkingTime> getWorkingTimes() {
        Mapper a = new Mapper<WorkingTime>() {
            @Override
            public List<WorkingTime> foo(ResultSet resultSet) {
                try {
                    List<WorkingTime> workingTimes = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        Date beginDate = resultSet.getTimestamp("beginDate");
                        Date endDate = resultSet.getTimestamp("endDate");
                        WorkingTime workingTime = new WorkingTime(id, beginDate, endDate);
                        workingTimes.add(workingTime);
                    }
                    return workingTimes;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        return executeSelectQuery("select * from workingtimes", a);
    }

    public void createDepartment(String departmentName) {
        executeCreateQuery(String.format("INSERT INTO departments(name) VALUES('%s')", departmentName));
    }
    public void createDepartmentHead(String fio, Long id_departmet) {
        executeCreateQuery(String.format("INSERT INTO departmentheads(fio, id_department) VALUES('%s', %d)", fio, id_departmet));
    }
    public void createDoctor(String fio, int id_department) {
        executeCreateQuery(String.format("INSERT INTO doctors(fio, id_department) VALUES('%s', %d)", fio, id_department));
    }
    public void createKindOfService(Long id_department, String name) {
        executeCreateQuery(String.format("INSERT INTO kindsofservice(name, id_department) VALUES('%s', %d)", name, id_department));
    }
    public void createPatient(String name, LocalDate dayOfBirth) {
        executeCreateQuery(String.format("INSERT INTO patients(fio, dayOfBirth) VALUES('%s', '%tF')", name, dayOfBirth));
    }
    public void createService(String name, BigDecimal price, long kindOfServiceID) {
        price = new BigDecimal(price.doubleValue()).setScale(2, RoundingMode.HALF_UP);
        executeCreateQuery(String.format("INSERT INTO services(name, price, id_kind) VALUES('%s', %d, %d)", name, (long)price.floatValue(), kindOfServiceID));
    }

    public void createServiceRegistration(int departmentID, int doctorID, int workTimeID, int serviceID, int patientID, int departmentHeadID, Date date, String comment) {
        executeCreateQuery(String.format("INSERT INTO serviceregistrations(dateOfRegistration, comment, id_department, id_doctor, id_workingTime, id_service, id_patient, id_departmentHead) VALUES('%tF', '%s', %d, %d, %d, %d, %d, %d)",
                                                                                                                                                                        date, comment, departmentID, doctorID, workTimeID, serviceID, patientID, departmentHeadID));
    }
    public void createWorkingTime(Date begindate, Date enddate) {
        executeCreateQuery(String.format("INSERT INTO workingtimes(beginDate, endDate) VALUES('%1$tF %1$tT', '%2$tF %2$tT')", begindate, enddate));
    }


    @Override
    public void updateDepartment(Department department) {
        executeUpdateQuery(String.format("UPDATE departments SET name = '%s' WHERE id = %d ", department.getName(), department.getId()));
    }

    @Override
    public void updateDepartmentHead(DepartmentHead departmentHead) {
        executeUpdateQuery(String.format("UPDATE departmentheads SET fio = '%s', id_department=%d  WHERE id = %2$d ", departmentHead.getFio(), departmentHead.getId_department(), departmentHead.getId()));
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        executeUpdateQuery(String.format("UPDATE doctors SET fio = '%s', id_department=%d WHERE id = %2$d", doctor.getFio(), doctor.getId_department(), doctor.getId()));
    }

    @Override
    public void updateKindOfService(KindOfService kindOfService) {
        executeUpdateQuery(String.format("UPDATE kindsofservice SET name = '%s', id_department=%d WHERE id = %2$d ", kindOfService.getName(), kindOfService.getId_department(), kindOfService.getId()));
    }

    @Override
    public void updatePatient(Patient patient) {
        executeUpdateQuery(String.format("UPDATE patients SET fio = '%s', dayOfBirth = '%tF' WHERE id = %d ", patient.getFio(),Date.from(patient.getDayOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()), patient.getId()));
    }

    @Override
    public void updateService(Service service) {
        executeUpdateQuery(String.format(Locale.UK, "UPDATE services SET name = '%s', price = %d, id_kind = %2$d WHERE id = %3$d", service.getName(), (long)service.getPrice().floatValue(), service.getId_kind(), service.getId()));
    }

    @Override
    public void updateServiceRegistration(ServiceRegistration serviceRegistration) {
        executeUpdateQuery(String.format("UPDATE serviceregistrations SET dateOfRegistration = '%tF'," +
                " comment = %s, id_department = %d, id_doctor = %2$d, id_workingTime = %3$d, id_service = %4$d, " +
                "id_patient = %5$d, id_departmentHead = %6$d  WHERE id = %7$d ", serviceRegistration.getDateOfRegistration(),
                serviceRegistration.getComment(), serviceRegistration.getId_department(), serviceRegistration.getId_doctor(),
                serviceRegistration.getId_workingTime(), serviceRegistration.getId_service(), serviceRegistration.getId_patient(),
                serviceRegistration.getId_departmentHead(), serviceRegistration.getId()));
    }

    @Override
    public void updateWorkingTime(WorkingTime workingTime) {
        executeUpdateQuery(String.format("UPDATE workingtimes SET beginDate = '%1$tF %1$tT', endDate = '%2$tF %2$tT' WHERE id = %3$d", workingTime.getBeginDate(), workingTime.getEndDate(), workingTime.getId()));
    }

    @Override
    public boolean removeDepartment(Department department) {
        executeDeleteQuery("departments", department.getId());
        return true;
    }

    @Override
    public boolean removeDepartmentHead(DepartmentHead departmentHead) {
        executeDeleteQuery("departmentheads", departmentHead.getId());
        return true;
    }

    @Override
    public boolean removeDoctor(Doctor doctor) {executeDeleteQuery("doctors", doctor.getId());
        return true;
    }

    @Override
    public boolean removeKindOfService(KindOfService kindOfService) {
        executeDeleteQuery("kindsofservice", kindOfService.getId());
        return true;
    }

    @Override
    public boolean removePatient(Patient patient) {
        executeDeleteQuery("patients", patient.getId());
        return true;
    }

    @Override
    public boolean removeService(Service service) {
        executeDeleteQuery("services", service.getId());
        return true;
    }

    @Override
    public boolean removeServiceRegistration(ServiceRegistration serviceRegistration) {
        executeDeleteQuery("serviceregistrations", serviceRegistration.getId());
        return true;
    }

    @Override
    public boolean removeWorkingTime(WorkingTime workingTime) {
        executeDeleteQuery("workingtimes", workingTime.getId());
        return true;
    }

    private <T> List<T> executeSelectQuery(String query, Mapper<T> a) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();){

            ResultSet resultSet = statement.executeQuery(query);

            return a.foo(resultSet);
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return null;
    }
    private void executeUpdateQuery(String query) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();){
             statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
    }
    private void executeCreateQuery(String query) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();){
            statement.execute(query);

        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
    }
    private void executeDeleteQuery(String table, Long id) {
        String query = String.format("DELETE FROM %s WHERE id=%d", table, id);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();){
            statement.execute(query);

        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
    }

}
