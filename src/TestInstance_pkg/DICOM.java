/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInstance_pkg;

import java.util.Date;

/**
 *
 * @author David Kloosterman
 */
public class DICOM {

//    public static final String TESTFILE_SAMPLE = ".\\TestImage.tif";
    String patient_id = null;
    Date timestamp = null;
    long clinicalTestImage_id = 0;
    long clinicalTestImage_length = 0;
    String clinicalTestFilePathInInstrument = null;

    public DICOM() {

    }

    @Override
    public String toString() {
        return "\nDICOM"
                + "\n   patient_id =\t\t" + patient_id
                + "\n   timestamp =\t\t" + timestamp
                + "\n   clinical test image ID =\t\t" + clinicalTestImage_id
                + "\n   clinical test image length =\t\t" + clinicalTestImage_length
                + "\n   clinical test image filename =\t\t" + clinicalTestFilePathInInstrument;
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

    public String getClinicalTestFilePathInInstrument() {
        return clinicalTestFilePathInInstrument;
    }

    public void setClinicalTestFilePathInInstrument(String clinicalTestFilePathInInstrument) {
        this.clinicalTestFilePathInInstrument = clinicalTestFilePathInInstrument;
    }

    public long getClinicalTestImage_length() {
        return clinicalTestImage_length;
    }

    public void setClinicalTestImage_length(long clinicalTestImage_length) {
        this.clinicalTestImage_length = clinicalTestImage_length;
    }

}
