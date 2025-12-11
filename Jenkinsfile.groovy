pipeline {
    agent {
        // Use the Kubernetes agent type
        kubernetes {
            defaultContainer 'maven'
            inheritFrom 'maven-agent'
            cloud 'openshift'
            // Optional: Specify a service account if different from the default Jenkins one
            // serviceAccount 'jenkins-agent'
        }
    }

    stages {

        stage('Clone Repo') {
            steps {
                // Manually specifies the repository URL and branch
                git branch: 'main',
                        credentialsId: 'github-token', // Optional: ID of a stored credential (e.g., SSH key)
                        url: 'https://github.com/secangkirkopipanas/todo-application.git'
            }
        }

        stage('Initialize and Checkout') {
            steps {
                script {
                    def logFile = "build-output.log"

                    echo "Running on dynamic Kubernetes agent with Maven..."
                    sh "mvn --version"
                    sh "mvn clean package >> ${logFile}"
                    echo "Build output is stored in '${logFile}'"

                    // 1. Archive the main application artifact (e.g., JAR/WAR)
                    archiveArtifacts artifacts: 'target/*.jar',
                            fingerprint: true, // Recommended: adds a cryptographic checksum for tracking usage
                            allowEmptyArchive: false

                    // Keep the log file as a build artifact on Jenkins
                    archiveArtifacts artifacts: logFile
                }
            }
        }
    }
}