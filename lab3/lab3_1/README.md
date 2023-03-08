# Lab3

### a) Couple of examples that use AssertJ expressive methods chaining.


- A_EmployeeRepositoryTest
  - assertThat(fromDb).isNotNull();

- B_EmployeeService_UnitTest
  - assertThat(doesEmployeeExist).isEqualTo(true);


- D_EmployeeRestControllerIT
  - assertThat(found).extracting(Employee::getName).containsOnly("bob");


- E_EmployeeRestControllerTemplateIT
  - assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    
### b) An example in which you mock the behavior of the repository
- In B_EmployeeService_UnitTest the repository is mocked,using @Mock, and in SetUp is setted what repository should return


    


