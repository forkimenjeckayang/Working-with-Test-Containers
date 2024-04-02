import com.bismark.test.com.bismark.Student;
import com.bismark.test.com.bismark.StudentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
public class StudentServiceIntegrationTest {

    static PostgreSQLContainer<?> postgreSQLContainer;

    @Autowired
    private StudentService studentService;

    @DynamicPropertySource
    static void postgreSQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> postgreSQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> postgreSQLContainer.getPassword());
    }

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.3")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpassword");
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setName("assah");
        student.setEmail("assah@jmail.com");

        Student savedStudent = studentService.addStudent(student);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertEquals(student.getName(), savedStudent.getName());
        Assertions.assertEquals(student.getEmail(), savedStudent.getEmail());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student();
        student.setName("assah");
        student.setEmail("assah@yahoo.com");

        Student savedStudent = studentService.addStudent(student);
        Assertions.assertNotNull(savedStudent);

        studentService.deleteStudent(savedStudent.getId());
        Assertions.assertNull(studentService.findStudentById(savedStudent.getId()));
    }
}