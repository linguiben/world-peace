package com.jupiter.admin.py;

import java.io.*;

public class PythonInteractiveClient {
    public static void main(String[] args) throws IOException {
        // Start Python subprocess
        ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/python3"
                , "/Users/jupiter/14.idea-workspace/world-peace/world-peace-admin/src/main/java/com/jupiter/admin/py/interactive_python.py");
        pb.redirectErrorStream(true); // Merge error stream with output stream

        Process process = pb.start();

        // Get input/output streams
        BufferedWriter pythonInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        BufferedReader pythonOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // User console input
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to Python. Enter commands (type 'exit' to quit).");

        Boolean isPythonReady = true;
        String userInput;
        try {
            while (isPythonReady &&
                    !(userInput = consoleInput.readLine()).equalsIgnoreCase("exit")) {
                if (!process.isAlive()) {
                    System.out.println("Python process has terminated.");
                    break;
                }

                // Send input to Python
                pythonInput.write(userInput);
                pythonInput.newLine();
                pythonInput.flush();

                // Read Python response
                String response;
                while ((response = pythonOutput.readLine()) != null) {
                    System.out.println("Python response: " + response);

                    // Break if prompt or waiting for input
                    if (response.startsWith("Python") || response.contains("waiting for command")) {
                        break;
                    }

                    if (response.contains("退出程序...")) {
                        System.out.println("Python process has exited.");
                        isPythonReady = false;
                        break;
                    }
                }
                if (response == null) {
                    System.out.println("No response from Python. Exiting loop.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error communicating with Python: " + e.getMessage());
        } finally {
            // Close streams and terminate process
            try {
                pythonInput.write("exit");
                pythonInput.newLine();
                pythonInput.flush();
            } catch (IOException e) {
                System.err.println("Error sending exit command to Python: " + e.getMessage());
            } finally {
                try {
                    pythonInput.close();
                    pythonOutput.close();
                } catch (IOException e) {
                    System.err.println("Error closing streams: " + e.getMessage());
                }
                process.destroy();
                System.out.println("Python process terminated.");
            }
        }
    }
}