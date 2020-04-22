package streamActivity;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@SuppressWarnings("WeakerAccess")
public class Library {
    private final List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    /**
     * Task 1: Finds the titles of all the books by the given author
     */
    public List<String> findTitlesByAuthor(String authorName) {
        List<String> result;
        result = books.stream()
                .filter(d -> d.getAuthor().equals(authorName))
                .map(Book::getTitle)
                .collect(toList());
        return result;
    }

    /**
     * Task 2: Find the titles of the books with more than the given minimum number of pages.
     */
    public List<String> findTitlesOfLongBooks(int minPageCount) {
        List<String> result = new ArrayList<>();
        for (Book d : books) {
            if (d.getPageCount()>minPageCount) {
                result.add(d.getTitle());
            }
        }
        return result;
    }

    /**
     * Task 3: Describes the titles and authors of all the books in the given genre.
     */
    public List<String> findTitlesAndAuthorsInGenre(String genre) {
        List<String> result = new ArrayList<>();
        result = books.stream()
                .filter(d->d.getGenres().contains(genre))
                .map(Book::getTitle) //???
                .collect(toList());
        return result;
    }

    /**
     * Task 4: How many books in the given genre does the library have?
     */
    public long countBooksInGenre(String genre) {//do this
        long count = 0L;
        for (Book d : books) {
            if (d.getGenres().contains(genre)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Task 5: What are the n shortest books in the library?
     */
    public List<Book> findShortestBooks(int n) {
        List<Book> result = new ArrayList<>(books);
        result.sort(Comparator.comparing(Book::getPageCount));
        while (result.size() > n) {
            result.remove(result.size()-1);
        }


        return result;
    }

    /**
     * Task 6: Finds all books in the given genre, sorted from shortest to longest.
     */
    public List<Book> findBooksInGenreSortedByLength(String genre) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getPageCount))
                .filter(d->d.getGenres().contains(genre))
                .collect(toList());
    }

    /**
     * Task 7: What is the book with the shortest title?
     */
    public Book findBookWithShortestTitle() {
        int shortestTitleIndex = 0;
        int shortestTitleLength = 2147483647;
        if (books.size()>0) {
            for(Book book:books) {
                if (book.getTitle().length()<shortestTitleLength) {
                    shortestTitleLength = book.getTitle().length();
                    shortestTitleIndex = books.indexOf(book);
                }
            }
            return books.get(shortestTitleIndex);
        }
        else {
             return null;
        }
    }

    /**
     * Task 8: Print all books in the library to STDOUT.
     */
    public void printLibrary() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // ––––––––––––  Special bonus challenges! ––––––––––––

    /**
     * What is the average number of pages of books in the library?
     * @return 0 if there are no books.
     */
    public double computeAverageBookLength() {
        return books.stream()
            .mapToInt(Book::getPageCount) // Note the special mapToInt() method to get primitives instead of objects
            .summaryStatistics()          // Stream of primitives gives us summaryStatistics() method
            .getAverage();
    }

    /**
     * Has the given author written any books in the given genre?
     *
     * Hint: Use anyMatch()
     */
    public boolean hasAuthorWrittenInGenre(String author, String genre) {
        for (Book book : books) {
            if (book.getAuthor().equals(author) && book.getGenres().contains(genre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gives a set containing all authors in the books.
     *
     * A Set is a bit like a List, with two differences:
     *
     * - It contains no duplicates; an element can only be in a set once.
     * - It does not preserve the order of the elements. They might not come out in the same order
     *   you put them in.
     *
     * Hint: If you want to make a new mutable Set, use new HashSet<>().
     */
    public Set<String> getAllAuthors() {
        return books.stream()
            .map(Book::getAuthor)
            .collect(toSet());
    }

    /**
     * What are the names of all the authors who have written in the given genre?
     */
    public Set<String> allAuthorsInGenre(String genre) {
        Set<String> result = new HashSet<>();
        for (Book book : books) {
            if (book.getGenres().contains(genre)) {
                result.add(book.getAuthor());
            }
        }
        return result;
    }

    /**
     * What are all the different genres in the library?
     *
     * Hint: flatMap() is like map(), except the lambda returns a stream, and all the
     *       elements in that returned stream feed into the larger stream one at a time
     *       instead of as a whole collection all at once.
     */
    public Set<String> getAllGenres() {
        Set<String> set = new HashSet<>();
        for (Book book : books) {
            set.addAll(book.getGenres());
        }
        return set;
    }
}
