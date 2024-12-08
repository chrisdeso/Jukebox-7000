Documentation for Running the Music App

Prerequisites
To run the Music App, you will need the following software installed on your machine:

Java Development Kit (JDK): Ensure you have JDK 8 or higher installed. You can download it from the Oracle website or use OpenJDK.

Apache Maven (optional): If you are using Maven for dependency management, ensure it is installed. You can download it from the Apache Maven website.

Integrated Development Environment (IDE): It is recommended to use an IDE like Visual Studio Code with the Java Extension Pack installed for a better development experience.

JavaFX: Since the application uses JavaFX for the user interface, ensure you have JavaFX libraries included in your project. You can download JavaFX from the Gluon website.

Project Structure
The project has the following folder structure:

src: Contains the source code files.
lib: Contains any external libraries or dependencies.
bin: Compiled output files will be generated here.
Running the Application
Clone the Repository: If you haven't already, clone the repository to your local machine.

Running the Application
1) Clone the Repository: If you haven't already, clone the repository to your local machine.

git clone <repository-url>
cd MusicApp

2) Open the Project in Your IDE: Open the project folder in your IDE (e.g., Visual Studio Code).

3) Build the Project: If you are using Maven, run the following command in the terminal to build the project:

mvn clean install 

4) Run the Application: You can run the application by executing the MusicAppMain class. In your IDE, locate the MusicAppMain.java file and run it. If you are using the command line, navigate to the bin directory and run:

java --enable-preview -cp bin:lib/* MusicAppMain

Adding Songs
To add songs to the music library:
1) Open the Application: Launch the Music App.

2) Use the Add Music Button: In the main window, you will see an "Add Music" button. Click on it.

3) Enter the File Path: A dialog will prompt you to enter the music file path. Provide the full path to the music file you want to add.

4) Confirm Addition: The song will be added to the library, and you can see it in the song list.

Searching for Songs
1) Enter Search Query: Use the search field at the top of the application window to enter a song title or artist name.

2) Click Search: Click the "Search" button to filter the songs based on your query.

3) Select a Song: From the results displayed in the list, select a song to play.

Playback Controls
Play: Click the "Play" button to start playing the selected song.

Pause: Click the "Pause" button to pause playback.

Skip: Click the "Skip" button to skip to the next song.

Back: Click the "Back" button to go back to the previous song.

Volume Control
The application allows you to adjust the volume using the volume control methods implemented in the AdjustVolume class. You can increase, decrease, or mute the volume as needed.