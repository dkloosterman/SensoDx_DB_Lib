package TestInstance_pkg;

import Cartridge_pkg.Cartridge;
import Instrument_pkg.Instrument;
//import java.sql.Timestamp;
import java.util.Date;
import JDBCqueries_pkg.JDBCqueries;
import Errors_pkg.Errors;
import static java.lang.Integer.toBinaryString;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Kloosterman
 */
public class TestInstance {

    // Assay Test Types
    public static final int ASSAY_FIELD_LENGTH = 16;
    public static final int NO_ASSAY_TEST = 0b0000000000000000;
    public static final int CARDIAC_WELLNESS_TEST = 0b0000000000000001;
    public static final int ORAL_CANCER_TEST = 0b0000000000000010;
    public static final int TRUST_ME_ASSAYS_ENABLED = 0b1111111111111111;

//    public static final boolean UseDiagAlgHF_interface = false;

    /*
    CREATE TABLE Clinical_Test_Instance (
       cartridge_id VARCHAR (20),
       instrument_id VARCHAR (20),
       patient_id VARCHAR (20),
       technician_id VARCHAR (20),
       doctor_id VARCHAR (20),
       raw_assay_data VARCHAR (100),
       analysis_result VARCHAR (100),
       clinical_test_timestamp TIMESTAMP,
       PRIMARY KEY (cartridge_id ) );
     */
    long clinical_test_instance_counter = 0;
    String cartridge_id = null;
    String instrument_id = null;
    String patient_id = null;
    String technician_id = null;
    String doctor_id = null;
    List<Long> image_id_list;
    String image_id_str = null;

    double analysis_result = 0;
    Date clinical_test_timestamp = null;

    String testResultString = null;

    public DICOM dicom = null;
    JDBCqueries queries = null;

    public TestInstance(List<String> imagePaths) {
        image_id_list = new ArrayList<>();
        this.dicom = new DICOM(imagePaths);
        this.queries = new JDBCqueries();

    }

    public List<String> ImageIDstring2List() {
        List<String> images = new ArrayList<>();
        String[] idList = this.getImage_id_str().split(":");

        for (String id : idList) {
            images.add(id);
        }

        return (images);
    }

    public boolean verifyTestParameters(Instrument instrument, Cartridge cartridge) {
        boolean testResult = true;  // return true if test successfully processed

        try {
            this.testResultString = "Test Input Parameters Passed";

            this.instrument_id = instrument.getInstrument_id();
            this.cartridge_id = cartridge.getCartridge_id();

            // set up DICOM content
            this.dicom.setPatient_id(this.patient_id);
            this.dicom.setTimestamp(this.clinical_test_timestamp);

            if (queries.isCartridgeValidToUse(this.cartridge_id)) {

                if ((instrument.getAssay_types_enabled() & cartridge.getAssay_type()) > 0) {

                    if (queries.insertClinicalTestImages(this.dicom)) {

                        List<TestImage> images = this.dicom.testImages;
                        this.image_id_list.clear();
                        this.image_id_str = "";
                        Long id;

                        for (TestImage image : images) {

                            id = image.getClinicalTestImage_id();
                            this.image_id_list.add(id);
                            this.image_id_str += id.toString() + ":";
                        }

                    } else {
                        Errors error = new Errors();

                        error.buildErrorObject_UnableToAddClinicalTestImageToDatabase(this.instrument_id,
                                this.cartridge_id,
                                null);

                        this.queries.insertError(error);

                        this.testResultString = error.toString();
                        error = null;

                        testResult = false;
                    }

                } else {
                    Errors error = new Errors();

                    error.buildErrorObject_CartridgeNotCampatibleWithInstrument(this.instrument_id,
                            this.cartridge_id,
                            null);

                    this.queries.insertError(error);

                    this.testResultString = error.toString();

                    error = null;

                    testResult = false;
                }
            } else {
                Errors error = new Errors();

                error.buildErrorObject_CartridgeNotValidToUse(this.instrument_id,
                        this.cartridge_id,
                        null);

                this.queries.insertError(error);

                this.testResultString = error.toString();

                error = null;

                testResult = false;
            }
        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            
            return (testResult);
        }   //end finally try

    }

    @Override
    public String toString() {
        return "Test Instance"
                + "\n   clinical_test_instance_counter = \t\t" + clinical_test_instance_counter
                + "\n   cartridge_id = \t\t" + cartridge_id
                + "\n   instrument_id = \t" + instrument_id
                + "\n   patient_id = \t\t" + patient_id
                + "\n   technician_id = \t" + technician_id
                + "\n   doctor_id = \t\t" + doctor_id
                + "\n   raw_assay_data = \t" + image_id_str
                + "\n   analysis_result = \t" + analysis_result
                + "\n   clinical_test_timestamp = \t" + clinical_test_timestamp
                + "\n"
                + dicom.toString();
    }
    
    public long getClinical_test_instance_counter() {
        return clinical_test_instance_counter;
    }

    public void setClinical_test_instance_counter(long clinical_test_instancce_counter) {
        this.clinical_test_instance_counter = clinical_test_instancce_counter;
    }

    public String getCartridge_id() {
        return cartridge_id;
    }

    public void setCartridge_id(String cartridge_id) {
        this.cartridge_id = cartridge_id;
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public List<Long> getImage_id_list() {
        return image_id_list;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getTechnician_id() {
        return technician_id;
    }

    public void setTechnician_id(String technician_id) {
        this.technician_id = technician_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getImage_id_str() {

        return image_id_str;
    }

    public void setImage_id_str(String data) {
        this.image_id_str = data;

    }

    public double getAnalysis_result() {
        return analysis_result;
    }

    public void setAnalysis_result(double analysis_result) {
        this.analysis_result = analysis_result;
    }

    public Date getClinical_test_timestamp() {
        return clinical_test_timestamp;
    }

    public void setClinical_test_timestamp(Date clinical_test_timestamp) {
        this.clinical_test_timestamp = clinical_test_timestamp;
    }

    public String getTestResultString() {
        return testResultString;
    }

    public void setTestResultString(String testResultString) {
        this.testResultString = testResultString;
    }

    public static String convertIntegerToBinaryString(int number, int binaryStringLength) {

        String binaryString = "0000000000000000" + toBinaryString(number);

        binaryString = binaryString.substring(binaryString.length() - ASSAY_FIELD_LENGTH, binaryString.length());

        return (binaryString);
    }

}
