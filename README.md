# ChatMotor API v1.0-beta

# Java Samples

<img src="https://docs.aceql.com/img/chatmotor-logo.png" width="200" alt="ChatMotor HTTP Icon"/>

# ChatMotor API Java Samples

Welcome to the [ChatMotor API](https://www.chatmotor.ai/) Java Samples repository! This project contains a collection of ready-to-use Java samples demonstrating how to integrate and utilize the powerful features of the ChatMotor API in your Java applications.

## Overview

ChatMotor is a versatile API designed to seamlessly integrate ChatGPT capabilities into Java applications. With a focus on simplifying complex tasks, ChatMotor offers a rich set of features, including:

- **Large File Handling**: Efficiently manage large files with automatic chunking and processing.
- **Advanced Transcription**: Transcribe audio files of any size without the 25MB OpenAI Whisper limitation.
- **Text-to-Speech**: Convert text to speech with no input size limitations.
- **Comprehensive Summarization**: Generate classic and strategic summaries for large text documents.
- **Robust Translation**: Translate documents with preserved formatting, including HTML and other text formats.
- **Error Handling**: Manage OpenAI errors with detailed extraction and handling mechanisms.
- **Document Conversion**: Convert various document formats to plain text using integrated utilities.

## Features

This repository includes sample code to help you get started with the following ChatMotor API functionalities:

- **Text Summarization**: Generate concise summaries of large text documents.
- **Strategic Summarization**: Create high-level, strategic summaries focusing on key insights.
- **Document Translation**: Translate text and HTML documents, with future support for additional formats.
- **Audio Transcription**: Transcribe audio files of any size into text.
- **Text-to-Speech Conversion**: Convert text to speech and save it as audio files.
- **Error Management**: Handle OpenAI errors effectively in your applications.
- **File Conversion Utilities**: Convert various document formats to plain text for further processing.

## Prerequisites

Before running the samples, ensure you have the following prerequisites:

- **Java Development Kit (JDK)**: Version 11 or higher.
- **Maven**: For managing project dependencies.
- **ChatMotor License** **File**: Obtain your ChatMotor license file from the [https://www.chatmotor.ai/](https://www.chatmotor.ai/) website.
- **ChatGPT**: Obtain an API key from the [OpenAI Website](https://openai.com/).

## Getting Started

**Clone the Repository**:

```sh
git clone https://github.com/yourusername/chatmotor-java-samples.git
```

**Set Up Environment Variables**:

Ensure that the following environment variables are set:

```sh
export MOTOR_LICENSE_FILE_PATH=/path/to/chatmotor_license_key.txt
export MOTOR_API_KEY=your_openai_api_key
```

**Install test files** 

Move the test files from `src/main/resources` to `<user.home>/chatmotor-samples` (or to any directory defined by the `SamplesParms.SAMPLE_FILES_DIR` static String.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/ChatMotorApi/chatmotor-java-samples?tab=MIT-1-ov-file#readme) file for more details.

## Support

If you encounter any issues or have questions, please open an issue on GitHub or contact our support team via the [ChatMotor Contact Page](https://www.chatmotor.ai/contact/).
