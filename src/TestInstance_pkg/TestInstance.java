package TestInstance_pkg;

import Cartridge_pkg.Cartridge;
import Instrument_pkg.Instrument;
import java.sql.Timestamp;
import java.util.Date;
import JDBCqueries_pkg.JDBCqueries;
import static java.lang.Integer.toBinaryString;

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
    long raw_assay_data = 0;
    double analysis_result = 0;
    Date clinical_test_timestamp = null;

    String testResultString = null;

    public DICOM dicom = null;
    JDBCqueries queries = null;

    public TestInstance(String pathToRawAssayFile) {
        this.dicom = new DICOM();
        this.dicom.setClinicalTestFilePathInInstrument(pathToRawAssayFile);
        this.queries = new JDBCqueries();

    }

    public boolean processTest(Instrument instrument, Cartridge cartridge) {
        boolean testResult = true;  // return true if test successfully processed

        this.testResultString = "Test Successfully Completed";

        this.instrument_id = instrument.getInstrument_id();
        this.cartridge_id = cartridge.getCartridge_id();

        // insert future code to verify this cartridge with this instrument
        this.patient_id = "1234567890123456";
        this.technician_id = "Jane Technician";
        this.doctor_id = "Joe Doctor";

        this.clinical_test_timestamp = new Timestamp(System.currentTimeMillis());

        this.dicom.patient_id = this.patient_id;
        this.dicom.timestamp = this.clinical_test_timestamp;

        // test if this Cartridge is an assay test type supported by this Instrument
        if ((instrument.getAssay_types_enabled() & cartridge.getAssay_type()) > 0) {

            long insertImage_id
                    = queries.insertClinicalTestImage(this.dicom);

            if (insertImage_id > 0) {
                
                this.setAnalysis_result(Math.random());   // temp code here until algorithms integrated
                this.setRaw_assay_data(this.dicom.getClinicalTestImage_id());

                queries.insertClinicalTestInstance(this);

            } else {
                testResult = false;
                this.testResultString = "Failure: Unable to add Clinical Test Image to database";
            }

        } else {
            testResult = false;
            this.testResultString = "Failure: Cartridge is not compatible with assay tests supported by this Instrument";
        }

        return (testResult);
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
                + "\n   raw_assay_data = \t" + raw_assay_data
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

    public long getRaw_assay_data() {
        return raw_assay_data;
    }

    public void setRaw_assay_data(long raw_assay_data) {
        this.raw_assay_data = raw_assay_data;
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
