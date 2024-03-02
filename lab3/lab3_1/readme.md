a) Identify a couple of examples that use AssertJ expressive methods chaining.

        
        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());

        assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");



        




b) Identify an example in which you mock the behavior of the repository (and avoid involving a
database).

        @Mock( lenient = true)
        private EmployeeRepository employeeRepository;



c) What is the difference between standard @Mock and @MockBean?

    
d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be
used?
e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with
SpringBoot. Which are the main/key differences?