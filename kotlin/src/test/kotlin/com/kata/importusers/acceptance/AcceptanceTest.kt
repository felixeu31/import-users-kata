package com.kata.importusers.acceptance

import main
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class AcceptanceTest {

    @Test
    fun testPrintedOutput() {
        val expectedOutput = """*********************************************************************************
* ID			* COUNTRY		* NAME				* EMAIL						*
*********************************************************************************
* 200189617246	* Germany		* Lukas Schmidt		* lukas.shmidt@example.com	*
* 200189016257	* Germany		* Maria Fischer		* maria.fischer@example.com	*
* 230573109005	* Spain 		* Luis Sanchez		* luis.sanchez@example.com	*
* 230854119034	* Italy 		* Elio Pausini		* elio.pausini@example.com	*
* 270054311737	* India 		* Mitesh Kumari		* mitesh.kumari@example.com	*
* 202160712259	* Germany		* Elena Mueller		* elena.muller@example.com	*
* 270554319031	* India 		* Natasha Shah		* natasha.shah@example.com	*
* 100000000001	* Australia		* Nevaeh Dunn		* nevaeh.dunn@example.com	*
* 100000000002	* Norway		* Sara Abdallah		* sara.abdallah@example.com	*
* 100000000003	* France		* Melvin Perrin		* melvin.perrin@example.com	*
* 100000000004	* Australia		* Dawn Snyder		* dawn.snyder@example.com	*
* 100000000005	* Netherlands		* Irina Kaptein		* irina.kaptein@example.com	*
*********************************************************************************
12 users in total!
"""

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Run your main function or program entry point here
        main(emptyArray())

        val actualOutput = outputStream.toString().replace("\r","")

        assertEquals(expectedOutput, actualOutput)
    }
}
