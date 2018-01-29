/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInstance_pkg;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.stream.Collectors;

/**
 *
 * @author David Kloosterman
 */
public class DICOM {

//    public static final String TESTFILE_SAMPLE = ".\\TestImage.tif";
    String patient_id = null;
    Date timestamp = null;  // also used as DICOM Test ID
    List<TestImage> testImages;

    
    public DICOM(List<String> imagePaths) {

        testImages = new ArrayList<>();
        for (String image : imagePaths) {
            this.testImages.add(new TestImage(image));
        }
    }

    @Override
    public String toString() {   // add support for Multi images
        String stringOfAllTestImages = "";
        for (TestImage image : this.testImages) {
            stringOfAllTestImages += image.toString() + '\n';
        }

        return "\nDICOM"
                + "\n   patient_id =\t\t" + patient_id
                + "\n   timestamp =\t\t" + timestamp
                + '\n' + stringOfAllTestImages;
    }

    public void addTestImage(TestImage path){
        this.testImages.add(path);
    }
    
    public void clearImageList(){
        this.testImages.clear();
    }
    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<TestImage> getTestImages() {
        return testImages;
    }

}
