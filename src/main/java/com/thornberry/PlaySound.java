package com.thornberry;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class PlaySound {
    private final String filename;

    public PlaySound(String wavfile) {
        this.filename = wavfile;
    }

    public void play() {
        try (InputStream resource = PlaySound.class.getResourceAsStream("/" + filename)) {
            if (resource == null) {
                System.err.println("Sound file not found: " + filename);
                return;
            }

            try (AudioInputStream ais = AudioSystem.getAudioInputStream(resource)) {
                AudioFormat format = ais.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);

                clip.addLineListener(event -> {;
                    if (event.getType() == LineEvent.Type.STOP || event.getType() == LineEvent.Type.CLOSE) {
                        clip.close();
                    }
                });

                clip.open(ais);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
