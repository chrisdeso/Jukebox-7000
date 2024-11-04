The Filtered Library Search sequence diagram illustrates the interactions between the
main components of the song app when a user searches for songs based on specific
criteria, such as Genre, Artist, or Album. The process begins with the User selecting a filter
type and entering a search term in LibraryView, which serves as the user interface for the
app's library section.

LibraryView receives the filter input and forwards it to SearchController by invoking the
filterSongs() method. SearchController is responsible for managing the search logic and
coordinating the filtering process. It sends a querySongsByFilter() request to
SongDatabase, which stores all the song data.

SongDatabase processes the filter criteria, retrieves a list of Song objects matching the
specified criteria, and returns this list (filteredSongs) to SearchController. To ensure that
all necessary details for each song are accessible, SearchController iterates through the
filteredSongs list, invoking the retrieveDetails() method on each Song object. Each Song
object responds with its details, such as title, artist, album, and genre.

After processing the songs, SearchController returns the complete list of filtered songs to
LibraryView, which then displays the results to the User with displayFilteredResults(). This
structured sequence ensures that each component fulfills a distinct role: User initiates the
request, LibraryView handles the interface, SearchController manages business logic,
and SongDatabase provides data access. This modular structure allows for efficient data
flow and clear separation of responsibilities, making the system easier to maintain and
scale.