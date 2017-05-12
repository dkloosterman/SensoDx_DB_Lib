/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInstance_pkg;

import Cartridge_pkg.Cartridge;
import Instrument_pkg.Instrument;
//import Instrument_gui.TopScreen;
import JDBCqueries_pkg.JDBCqueries;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Owner
 */
public class TestInstanceTest {

    static final String CARDIACWELLNESSINSTRUMENT = "2017040300001";
    static final String ORALCANCERINSTRUMENT = "2017040300002";
    static final String CARDIACWELLNESSCARTRIDGE = "20170501085526993";
    static final String ORALCANCERCARTRIDGE = "20170501085526994";

    public static final String TESTFILE_SAMPLE = ".\\TestImage.tif";

    JDBCqueries queries = null;

    public TestInstanceTest() {

        this.queries = new JDBCqueries();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processTest method, of class TestInstance.
     */
    @Test
    public void testProcessTest() {
       System.out.println("processTest");

        Instrument instrumentForCardiacWellness = new Instrument();
        Instrument instrumentForOralCancer = new Instrument();
        Cartridge cartridgeForCardiacWellness = new Cartridge();
        Cartridge cartridgeForOralCancer = new Cartridge();

        TestInstance testInstance = new TestInstance(TESTFILE_SAMPLE);

        System.out.println("Get CARDIACWELLNESSINSTRUMENT: " + CARDIACWELLNESSINSTRUMENT);
        queries.getInstrumentMfgInfo(CARDIACWELLNESSINSTRUMENT, instrumentForCardiacWellness);
        queries.getInstrumentDeploymentInfo(CARDIACWELLNESSINSTRUMENT, instrumentForCardiacWellness);
        System.out.println(instrumentForCardiacWellness.toString());

        System.out.println("Get ORALCANCERINSTRUMENT: " + ORALCANCERINSTRUMENT);
        queries.getInstrumentMfgInfo(ORALCANCERINSTRUMENT, instrumentForOralCancer);
        queries.getInstrumentDeploymentInfo(ORALCANCERINSTRUMENT, instrumentForOralCancer);
        System.out.println(instrumentForOralCancer.toString());

        System.out.println("Get CARDIACWELLNESSCARTRIDGE: " + CARDIACWELLNESSCARTRIDGE);
        queries.getCartridgeMfgInfo(CARDIACWELLNESSCARTRIDGE, cartridgeForCardiacWellness);
        System.out.println(cartridgeForCardiacWellness.toString());

        System.out.println("Get ORALCANCERCARTRIDGE: " + ORALCANCERCARTRIDGE);
        queries.getCartridgeMfgInfo(ORALCANCERCARTRIDGE, cartridgeForOralCancer);
        System.out.println(cartridgeForOralCancer.toString());

        // Test Cardiac Wellness Instrument with Cardiac Wellness Cartridge
        System.out.println("Start Test Cardiac Wellness Instrument with Cardiac Wellness Cartridge: ");
        assertTrue(testInstance.processTest(instrumentForCardiacWellness, cartridgeForCardiacWellness));
        System.out.println("Completed Test Cardiac Wellness Instrument with Cardiac Wellness Cartridge: ");

        // Test Oral Cancer Instrument with Oral Cancer Cartridge
        System.out.println("Start Test Oral Cancer Instrument with Oral Cancer Cartridge: ");
        assertTrue(testInstance.processTest(instrumentForOralCancer, cartridgeForOralCancer));
        System.out.println("Completed Test Oral Cancer Instrument with Oral Cancer Cartridge: ");

        // Test Cardiac Wellness Instrument with Oral Cancer Cartridge
        System.out.println("Start Test Cardiac Wellness Instrument with Oral Cancer Cartridge: ");
        assertFalse(testInstance.processTest(instrumentForCardiacWellness, cartridgeForOralCancer));
        System.out.println("Completed Test Cardiac Wellness Instrument with Cardiac Wellness Cartridge: ");

        // Test Oral Cancer Instrument with Cardiac Wellness Cartridge
        System.out.println("Start Test Oral Cancer Instrument with Cardiac Wellness Cartridge: ");
        assertFalse(testInstance.processTest(instrumentForOralCancer, cartridgeForCardiacWellness));
        System.out.println("Completed Test Cardiac Wellness Instrument with Cardiac Wellness Cartridge: ");

    }

    

}
