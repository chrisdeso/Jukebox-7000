Chris de Sousa, Nguyen_Khanh
CS3354 - Fall 2024

UML Sequence Diagram


## **Sequence Diagram Description**

#### Account Creation, Account && Song

These components illustrate the interactions between the user, and the system, during the following actions:

- Creating an account
- Accessing an existing account
- Interacting with a song

In the account creation flow, the user sends a `createAccount` request to the `CreateAccount` Component, which then checks if the username is available, validates the username and password, and creates the account. During the login process (Account), the user will send a `login` request to the `Account` component which validates the information passed in the request (username + password). For song playback, library population, and filtering, the user receives a list of songs from the `Song` component to enable playback, filtering, etc.

#### Library View, SearchController, SongDatabase, Song

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

#### System Components

This sequence diagram simulates a typical user interaction with a music system application involving file management and volume adjustment. The user initiates by setting the system’s status to active, which they achieve through the setStatus method in the System class. This action confirms that the system is online and ready to accept further commands.

Next, the user requests the system’s name via the getName method, which could be useful for identifying or labeling the system in a larger application. The system returns the name (e.g., "Music System") to the user.

Following this, the user decides to add a new music file, "song.mp3," using the AddMusic class. When addFile is called on AddMusic, it triggers an update to the system’s FileList attribute. The System class acknowledges this update, confirming that the file list has been successfully modified to include the new file.

With the music file now available, the user adjusts the volume by invoking the volumeUp method in the AdjustVolume class. This action increases the system’s volume level, allowing the user to set a comfortable listening level.
The user then calls getFileList on the System class to view the available files. The system returns an updated list that includes "song.mp3," confirming that the file addition was successful.

Finally, the user mutes the volume by calling mute in the AdjustVolume class, bringing the volume to zero. This sequence demonstrates how users can interact with a music system by adding files, managing settings, and controlling playback volume through a series of method calls across interconnected classes.