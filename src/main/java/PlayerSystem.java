import java.util.ArrayList;
import java.util.List;

// Main music system
class MusicSystem
{
    private boolean status;
    private String name = "Music System";
    private List<String> fileList = new ArrayList(); // List of music files
    private int volume = 50; // Default volume

    public void setStatus(boolean status)
    {
        this.status = status;
        System.out.println("Status set: " + status);
    }

    public String getName()
    {
        return name;
    }

    public List<String> getFileList()
    {
        return fileList;
    }

    public void updateFileList(String fileName, boolean isAdding)
    {
        if (isAdding)
        {
            fileList.add(fileName);
        }
        else
        {
            fileList.remove(fileName);
        }
        System.out.println("FileList Updated: " + fileList);
    }
    public int getVolume()
    {
        return volume;
    }

    public void setVolume(int volume)
    {
        this.volume = volume;
        System.out.println("Volume set to " + volume);
    }
}

// Handle adding music files
class AddMusic
{
    private MusicSystem system;

    public AddMusic(MusicSystem system)
    {
        this.system = system;
    }

    public void addFile(String fileName)
    {
        system.updateFileList(fileName, true);
    }

}

// Handle deleting music files
class DeleteMusic
{
    private MusicSystem system;

    public DeleteMusic(MusicSystem system)
    {
        this.system = system;
    }
    
    public void deleteFile(String fileName)
    {
        system.updateFileList(fileName, false);
    }
}

// Handles volume adjustments
class AdjustVolume
{
    private MusicSystem system;

    public AdjustVolume(MusicSystem system)
    {
        this.system = system;
    }
    
    public void volumeUp()
    {
        int currentVolume = system.getVolume();
        system.setVolume(currentVolume + 10);
    }

    public void volumeDown()
    {
        int currentVolume = system.getVolume();
        system.setVolume(currentVolume - 10);
    }

    public void mute()
    {
        system.setVolume(0);
    }
}

// Main class to test the system
public class PlayerSystem
{
    public static void main(String[] args) 
    {
        // initialize components
        MusicSystem system = new MusicSystem();
        AddMusic addMusic = new AddMusic(system);
        DeleteMusic deleteMusic = new DeleteMusic(system);
        AdjustVolume adjustVolume = new AdjustVolume(system);

        // Set status
        system.setStatus(true);

        // Get the system name
        System.out.println("System Name: " + system.getName());

        // Add music
        addMusic.addFile("song.mp3");

        // Delete music
        deleteMusic.deleteFile(("song.mp3"));

        // Adjust volume
        adjustVolume.volumeUp();
        adjustVolume.mute();

        // Get file list
        System.out.println("File List: " + system.getFileList());
    }
}