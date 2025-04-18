package com.example.androidapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    private lateinit var device: UiDevice
    private val packageName = "com.example.androidapp"
    private val timeout = 5000L

    @Before
    fun setUp() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), timeout)

        // Launch the app
        val appIcon = device.findObject(UiSelector().descriptionContains("MobileSoftwareEngineeringApp"))
        appIcon.clickAndWaitForNewWindow(timeout)
    }

    @Test
    fun testExplicitActivityLaunchAndChallengeDisplay() {
        // Wait for the main activity to load
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), timeout)

        // Find and click the "Start Activity Explicitly" button
        val explicitButton = device.findObject(UiSelector().text("Start Activity Explicitly"))
        explicitButton.clickAndWaitForNewWindow(timeout)

        // Verify that the SecondActivity is displayed by checking for one of the challenges
        val challengeText = device.findObject(UiSelector().text("1. Software Security"))
        assert(challengeText.exists()) { "Software Security challenge text not found in SecondActivity" }
    }
}