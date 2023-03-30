pipeline {
     environment { 
        NEXUS_USER = "admin"
        NEXUS_PASSWORD = "admin123"
        registry = "islem23/tpachat23" 
        registryCredential = 'dockerHub' 
        dockerImage = '' 
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCAOL = "http"
        NEXUS_URL = "172.10.0.140:8081"
        NEXUS_REPOSITORY = "nexus-repo-devops"
        NEXUS_CREDENTIAL_ID = "deploymentRepo"
    }


    agent any
    stages {
        stage('Build') {
            steps {
               sh 'mvn clean package'
            }
        }
        stage('Unite Test') {
            steps {
                 sh 'mvn test'
            }
        }
        stage ('SonarQube') {
            steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin123'

            }
        }
        stage('PUBLISH TO NEXUS') {
            steps {
                sh 'make deploy'
            }
        }
    }
}

