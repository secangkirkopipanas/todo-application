pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh '${MAVEN_HOME}/bin/mvn -B -DskipTests clean package'
            }
        }
//        stage('Test') {
//            steps {
//                sh '${MAVEN_HOME}/bin/mvn test'
//            }
//            post {
//                always {
//                    junit 'target/surefire-reports/*.xml'
//                }
//            }
//        }
    }
}