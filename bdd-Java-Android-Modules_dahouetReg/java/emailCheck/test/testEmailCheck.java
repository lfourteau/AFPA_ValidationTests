/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import emailcheck.EmailCheck;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author afpa
 */
public class testEmailCheck {

    public testEmailCheck() {
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

    @Test
    public void testEmailTrue() {
        assertTrue(EmailCheck.checkEmail("lusq@sqsqd.com"));
        assertTrue(EmailCheck.checkEmail("lusq.q@sqsqd.com"));
        assertTrue(EmailCheck.checkEmail("lusq@sqs.qd.com"));
    }
    
    @Test
    public void testEmailFalse() {
        assertFalse(EmailCheck.checkEmail("lusqcomcom"));
        assertFalse(EmailCheck.checkEmail("lusqcom.com"));
        assertFalse(EmailCheck.checkEmail("lusq@com"));
        assertFalse(EmailCheck.checkEmail("lusqcom.qdzqd@com"));
        assertFalse(EmailCheck.checkEmail("d@com.com"));
        assertFalse(EmailCheck.checkEmail("lusq@s.com"));
        assertFalse(EmailCheck.checkEmail("lusq@sqz.c"));
    }

}
