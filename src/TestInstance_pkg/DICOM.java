/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInstance_pkg;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author David Kloosterman
 */
public class DICOM {

//    public static final String TESTFILE_SAMPLE = ".\\TestImage.tif";
    String patient_id = null;
    Date timestamp = null;
    
    // NEED TO ADD SUPPORT FOR MULTI IMAGES... maybe add a new Class TestImage?
    long clinicalTestImage_id = 0;  // to List<long>
    long clinicalTestImage_length = 0;   // to List<long>
    List<Long> ids;
    List<Long> lengths;
    List<String> testImagePaths;

    public DICOM(List<String> imagePaths) {
        this.testImagePaths = imagePaths.stream().collect(Collectors.toList());

    }

    @Override
    public String toString() {   // add support for Multi images
        return "\nDICOM"
                + "\n   patient_id =\t\t" + patient_id
                + "\n   timestamp =\t\t" + timestamp
                + "\n   clinical test image ID =\t\t" + clinicalTestImage_id
                + "\n   clinical test image length =\t\t" + clinicalTestImage_length
                + "\n   clinical test image filenames =\t" + getTestImagePathsToString();
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

    public long getClinicalTestImage_id() {
        return clinicalTestImage_id;
    }

    public void setClinicalTestImage_id(long clinicalTestImage_id) {
        this.clinicalTestImage_id = clinicalTestImage_id;
    }

    public List<String> getClinicalTestFilePathsInInstrument() {
        return testImagePaths;
    }

    String getTestImagePathsToString() {
        return String.join(", \n\t\t", testImagePaths);
    }

    public long getClinicalTestImage_length() {
        return clinicalTestImage_length;
    }

    public void setClinicalTestImage_length(long clinicalTestImage_length) {
        this.clinicalTestImage_length = clinicalTestImage_length;
    }

}
