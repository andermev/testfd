package images;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;

import java.util.ArrayList;
import java.util.List;

public class AWSImageFaceDetector {

    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    private static final String REGION = "eu-west-1";
    private AmazonRekognition client;

    public AWSImageFaceDetector() {
        client = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }

    public List<BoundingBox> detectFaces(Image image) {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(image);
        DetectFacesResult result = client.detectFaces(request);
        List<FaceDetail> faceDetails = result.getFaceDetails();

        List<BoundingBox> detections = new ArrayList<BoundingBox>();
        for(FaceDetail faceDetail: faceDetails) {
            detections.add(faceDetail.getBoundingBox());
        }
        return detections;
    }
}
