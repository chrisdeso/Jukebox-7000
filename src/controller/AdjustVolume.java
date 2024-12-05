package controller;

public class AdjustVolume {
    private int volume;

    public AdjustVolume() {
        this.volume = 50; // Default volume level
    }

    public void volumeUp() {
        if (volume < 100) {
            volume++;
            System.out.println("Volume increased to: " + volume);
            // Logic to set the volume in the media player
        }
    }

    public void volumeDown() {
        if (volume > 0) {
            volume--;
            System.out.println("Volume decreased to: " + volume);
            // Logic to set the volume in the media player
        }
    }

    public void mute() {
        volume = 0;
        System.out.println("Volume muted.");
        // Logic to mute the media player
    }

    public int getVolume() {
        return volume;
    }
}
