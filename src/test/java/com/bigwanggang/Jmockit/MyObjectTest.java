package com.bigwanggang.Jmockit;

import mockit.Expectations;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

@RunWith(JMockit.class)
public class MyObjectTest {
    @Tested
    MyObject obj;

    @Test
    public void testUnderTest() throws SQLException {
        new Expectations(StaticClass.class) {
            {
//                StaticClass.getSqlResult();
            }
        };
        obj.underTest();

    }
}
