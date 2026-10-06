pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                echo '========== BUILD APPLICATION =========='
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo '========== DEPLOY APPLICATION =========='
                sh '''
                    echo "Stopping old application..."
                    sudo systemctl stop myapp || true

                    echo "Copying new JAR..."
                    # Copies main executable jar, excluding 'original-*.jar'
                    find target/ -maxdepth 1 -name "*.jar" ! -name "original-*.jar" -exec cp {} /opt/myapp/app.jar \;

                    echo "Starting new application..."
                    sudo systemctl start myapp
                    echo "Application started"
                '''
            }
        }

        stage('Verify') {
            steps {
                echo '========== VERIFY APPLICATION =========='
                sh '''
                    sudo systemctl status myapp --no-pager
                '''
            }
        }
    }

    post {
        success {
            echo '========== DEPLOYMENT SUCCESS =========='
        }
        failure {
            echo '========== DEPLOYMENT FAILED =========='
        }
        always {
            echo '========== PIPELINE END =========='
        }
    }
}