package cc.core.file.utils;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;

public class MP3FileHelper {

    // 可以获取mp3文件的信息

    public static String mp3Time(){
        String strLen="";
        try {

            MP3File file = new MP3File("E:\\audio\\parent\\0001\\0022.mp3");
            MP3AudioHeader audioHeader = (MP3AudioHeader)file.getAudioHeader();

            strLen = audioHeader.getTrackLengthAsString();
            System.out.println(strLen);

            int intLen = audioHeader.getTrackLength();
            System.out.println(intLen);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }

        return strLen;
    }
}
