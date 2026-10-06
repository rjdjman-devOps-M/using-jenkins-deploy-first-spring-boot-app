pipeline {

    agent any

    stages {

        stage('Start') {
            steps {
                echo '========== BUILD STARTED =========='
            }
        }

        stage('Checkout') {
            steps {
                echo '========== SOURCE CODE CHECKOUT =========='
            }
        }

        stage('Build') {
            steps {
                echo '========== BUILDING SPRING BOOT =========='

                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Archive JAR') {
            steps {
                echo '========== GENERATED JAR =========='

                bat 'dir target\\*.jar'

                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

        stage('End') {
            steps {
                echo '========== BUILD COMPLETED =========='
            }
        }
    }
}