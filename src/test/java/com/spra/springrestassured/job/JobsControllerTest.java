package com.spra.springrestassured.job;


import com.spra.springrestassured.job.core.JobNotFoundException;
import com.spra.springrestassured.job.core.JobService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
class JobsControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:latest")
    );

    @LocalServerPort
    private Integer port;

    @Autowired
    JobService jobService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldAddNewJobPost(){
        given().contentType(ContentType.JSON)
                .body("""
                            {
                              "jobTitle": "Senior Backend Engineer (Java)",
                              "description": "My Company is looking for an experienced Java Backend developer.",
                              "jobType": "FULL_TIME",
                              "datePosted": "2024-03-24",
                              "jobLink": "https://mycompany.breezy.hr/p/22b17727d5f4-senior-backend-engineer-java"
                            }
                          """
                )
                .when()
                .post("/api/v1/jobs")
                .then()
                .statusCode(201)
                .header("Location", matchesRegex(".*/api/v1/jobs/[0-9]+$"))
                .body("id", notNullValue())
                .body("jobTitle", equalTo("Senior Backend Engineer (Java)"))
                .body("jobLink", equalTo("https://mycompany.breezy.hr/p/22b17727d5f4-senior-backend-engineer-java"));
    }

    @Test
    void shouldFindJobPostById(){
        when()
                .get("/api/v1/jobs/{jobId}", 10)
                .then()
                .statusCode(200)
                .body("id", equalTo(10))
                .body("jobTitle", equalTo("Speech Pathologist"))
                .body("datePosted", equalTo("2024-02-19"));
    }

    @Test
    void shouldFindAllAvailableJobPosts(){
        given()
                .queryParam("page", 2)
                .queryParam("size", 50)
                .when()
                .get("/api/v1/jobs")
                .then()
                .statusCode(200)
                .body("totalElements", equalTo(1000))
                .body("pageNumber", equalTo(2))
                .body("totalPages", equalTo(20))
                .body("isFirst", equalTo(false))
                .body("isLast", equalTo(false))
                .body("hasNext", equalTo(true))
                .body("hasPrevious", equalTo(true))
                .body("data.size()", equalTo(50));
    }

    @Test
    void shouldUpdateJobPost(){
        given().contentType(ContentType.JSON)
                .body("""
                            {
                              "jobTitle": "Electrical Engineer",
                              "description": "XYZ inc. Is looking for an experienced Electrical Engineer.",
                              "jobType": "CONTRACTOR",
                              "datePosted": "2023-09-18",
                              "jobLink": "https://xyz.bamboohr.hr/p/22b177hg675-electrical-engineer"
                            }
                          """
                )
                .when()
                .put("/api/v1/jobs/{jobId}", 3)
                .then()
                .statusCode(204);
    }

    @Test
    void shouldDeleteJobPost(){
        when()
                .delete("/api/v1/jobs/{jobId}", 50)
                .then()
                .statusCode(204);

        assertThatExceptionOfType(JobNotFoundException.class).isThrownBy(
                () -> jobService.findJobById(50L))
                .withMessage("Job with id: 50 not found!");
    }
}