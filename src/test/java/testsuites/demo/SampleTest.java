package testsuites.demo;

import org.testng.annotations.*;

public class SampleTest {


    @BeforeSuite
    void beforeSuite(){
        System.out.println("Before Suite");
    }

    @BeforeTest
    void beforeTest(){
        System.out.println("Before Test");
    }

    @BeforeClass
    void beforeClass(){
        System.out.println("Before Class");
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("Before Method");
    }

    @Test
    void tc01(){
        System.out.println("tc01");
    }

    @Test
    void tc02(){
        System.out.println("tc02");
    }
    @Test
    void tc03(){
        System.out.println("tc03");

    }
    @Test
    void tc04(){
        System.out.println("tc04");
    }
    @Test
    void tc05(){
        System.out.println("tc05");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("After Method");
    }
    @AfterClass
    void afterClass(){
        System.out.println("After Class");
    }

    @BeforeTest
    void afterTest(){
        System.out.println("After Test");
    }

    @BeforeSuite
    void afterSuite(){
        System.out.println("After Suite");
    }
}
/**
 * After Suite
 * Before Suite
 *
 * After Test
 * Before Test
 *
 * Before Class
     * Before Method
        * tc01
     * After Method
 * ---------------------
     * Before Method
        * tc02
     * After Method
 * ---------------------
     * Before Method
         * tc03
     * After Method
 * --------------------
     * Before Method
        * tc04
     * After Method
 * -------------------
     * Before Method
         * tc05
     * After Method
 * ----------------------
 * After Class
 */
