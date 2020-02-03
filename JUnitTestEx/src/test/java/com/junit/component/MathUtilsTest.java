package com.junit.component;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When running MathUtilTest")
class MathUtilsTest {

	MathUtils mathUtils;

	@BeforeAll
	/* static */void beforeAllInit() {
		/*
		 * In case of PER_CLASS it is optional to specify the @BeforeAll method as static
		 * because the instance will be created once for the all the test cases.
		 * PER_CLASS vs PER_METHOD 
		 */
		System.out.println("This needs to be run before all ");
	}
	
	@BeforeEach
	void init() {
		mathUtils = new MathUtils();
	}

	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up.....");
	}

	@Nested
	@DisplayName("Add Method")
	class Addtest {
		
		@Test
		@DisplayName("Testing add method positive numbers")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1, 1), "The add method should return the right sum");
		}
		@Test
		@DisplayName("Testing add method negative numbers")
		void testAddNegative() {
			assertEquals(-2, mathUtils.add(-1, -1), "The add method should return the right sum");
		}
	}
	
	@Test
	@DisplayName("Multiply method")
	void testMultiply() {
		//assertEquals(4, mathUtils.multiply(2, 2),"The multiply method should return right product");
		
		assertAll(
				()-> assertEquals(4, mathUtils.multiply(2, 2)),
				()-> assertEquals(0, mathUtils.multiply(2, 0)),
				()-> assertEquals(-2, mathUtils.multiply(2, -1))
				);
	}
	

	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0),
				"Divide by zero should throw ArithmeticException");
	}

	@RepeatedTest(3)
	void testComputeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right area");
	}
	
	@Test
	@Disabled
	@DisplayName("TDD method should not run")
	void testDisabled() {
		fail("This test should be disabled");
	}
	
	
	
}
