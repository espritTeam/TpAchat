pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                 sh 'mvn test'
            }
        }
        stage ('SonarQube') {
            steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin123'

            }
        
    }
}

