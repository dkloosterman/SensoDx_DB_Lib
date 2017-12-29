/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInstance_pkg;

/**
 *
 * @author Owner
 */
public class TestImage {

    long clinicalTestImage_id = 0;
    long clinicalTestImage_length = 0;
    final String testImagePath;

    @Override
    public String toString() {
        return "\nTest Image"
                + "\n   clinicalTestImage_id=\t\t" + clinicalTestImage_id
                + "\n   clinicalTestImage_length=\t\t" + clinicalTestImage_length
                + "\n   testImagePath=\t\t" + testImagePath;
    }

    public TestImage(String imagePath) {
        this.testImagePath = imagePath;
    }

    public long getClinicalTestImage_id() {
        return clinicalTestImage_id;
    }

    public void setClinicalTestImage_id(long clinicalTestImage_id) {
        this.clinicalTestImage_id = clinicalTestImage_id;
    }

    public long getClinicalTestImage_length() {
        return clinicalTestImage_length;
    }

    public void setClinicalTestImage_length(long clinicalTestImage_length) {
        this.clinicalTestImage_length = clinicalTestImage_length;
    }

    public String getTestImagePath() {
        return testImagePath;
    }

}
