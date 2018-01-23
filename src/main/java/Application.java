import images.AWSImageFaceDetectorAdapter;
import images.ImageFaceBlur;
import images.ImageFaceDetector;
import org.opencv.core.Core;
import videos.OpenCVVideoDetection;

public class Application {

    private static final String FFMPEG_LIBRARY_NAME = "opencv_ffmpeg340_64";
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary(FFMPEG_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        ImageFaceDetector detector = new AWSImageFaceDetectorAdapter();
        ImageFaceBlur imageFaceBlur = new ImageFaceBlur(detector);
        imageFaceBlur.processAndSave("input.jpg", "ouput.jpg");

        //OpenCVVideoDetection detection = new OpenCVVideoDetection(imageFaceBlur);
        //detection.process("video.avi", "ouput.avi");
    }
}
