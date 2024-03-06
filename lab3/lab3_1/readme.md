# Lab3_1

### a) Couple of examples that use AssertJ expressive methods chaining.


- A_EmployeeRepositoryTest
  - assertThat(fromDb).isNull();


- B_EmployeeService_UnitTest
  - assertThat(fromDb.getName()).isEqualTo("john");




- D_EmployeeRestControllerIT
  - assertThat(found).extracting(Employee::getName).containsOnly("bob");



- E_EmployeeRestControllerTemplateIT
  - assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    
    
### b) An example in which you mock the behavior of the repository
- In B_EmployeeService_UnitTest the repository is mocked,using @Mock, in SetUp is setted what repository should return.




### c) @Mock vs @MockBean

 - **@MockBean** is a SpringBoot's annotation who create a Mockito Mock and make the injection in the application context
 
 - while **@Mock** comes from Mockito framework and just create a Mock without injection
    
### d) “application-integrationtest.properties” Role and when used
- This file is used to set some properties to be able to run the app in a  different environment, for example use a persistence storage or set a port.
- and is just used in test files with @TestPropertySource annotation. In this case will run the test with the file's proprieties.

### e) Differences between C,D and E strategy

- The unique change from D to E ,is the E make REST requests using TestRestTemplate class while D use MockMvc, which dont allow this requests.
- The strategy C do a test exclusively focus in controller.