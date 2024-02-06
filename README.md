# Mobile Number OTP Verification Android Application

This Android application allows users to input their mobile numbers without a leading zero and sends a one-time password (OTP) for verification using Firebase Authentication.

## Features

- **Mobile Number Input**: Users can enter their mobile numbers without a leading zero.
- **OTP Generation and Verification**: The application generates and sends an OTP to the provided mobile number for verification using Firebase Authentication.
- **Error Handling**: Proper error handling is implemented for invalid inputs and failed OTP verification attempts.
- **User Experience**: The application provides a smooth user experience with clear instructions and feedback messages.

## Prerequisites

- Android Studio installed on your development machine.
- A Firebase project created and configured for your Android application. You will need to add your Android application to the Firebase project and download the `google-services.json` file to include in your Android project.

## Usage

1. Clone or download the application source code from the repository.
2. Open the project in Android Studio.
3. Replace the `google-services.json` file in the `app` directory with your own downloaded from the Firebase console.
4. Build and run the application on an Android device or emulator.

## Firebase Setup

1. Go to the Firebase console (https://console.firebase.google.com).
2. Create a new project or select an existing one.
3. Add an Android app to your Firebase project and follow the setup instructions to download the `google-services.json` file.
4. Enable Firebase Authentication in your project's Firebase console.
5. Configure the Firebase Authentication settings for phone number verification, including enabling phone number sign-in and specifying the allowed countries for phone number verification.

## Implementation Details

- **User Interface**: The application has a simple user interface with an input field for the mobile number and a button to trigger OTP verification.
- **Firebase Authentication**: Firebase Authentication is used to handle OTP generation, sending, and verification. The Firebase SDK provides APIs for these functionalities, making it easy to integrate with the Android application.
- **OTP Handling**: Once the OTP is sent to the user's mobile number, the application prompts the user to enter the received OTP. The entered OTP is then sent to Firebase Authentication for verification.

## Note

- Ensure that your Firebase project's billing and usage limits are configured appropriately to handle OTP verification SMS costs and usage.
- Test the application thoroughly on various devices and network conditions to ensure proper functionality and user experience.

