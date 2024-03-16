package io.cucumber.skeleton;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class BookSearchTest {
	Library library = new Library();
	List<Book> result = new ArrayList<>();

 

    public Date convert(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormat.parse(date);
            return date1;
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



	@Given("a book with the title {string}, written by {string}, published in {string}")
	public void addNewBook(final String title, final String author,  final String date) {
        Date publishedDate = convert(date);
        Book book = new Book(title, author, publishedDate);
        library.addBook(book);
	}

    @When("the customer searches for books published between {string} and {string}")
    public void setSearchParametersDate( final String f, final String t) {
		Date from = convert(f);
		Date to = convert(t);
		result = library.findBooksByDate(from, to);
    }
     
	@When("the customer searches for books written by {string}")
	public void setSearchParametersAuthor(final String author){
		result = library.findBooksByAuthor(author);
	}

	@When("the customer searches for books with title {string}")
	public void setSearchParametersTitle(final String title){
		result = library.findBooksByTitle(title);
	}

	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertEquals(result.size(), booksFound);
	}

	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertEquals(result.get(position - 1).getTitle(), title);
	}

}

