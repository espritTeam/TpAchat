pipeline {
     environment { 
        NEXUS_USER = "admin"
        NEXUS_PASSWORD = "admin123"
        registry = "islem23/tpachat23" 
        registryCredential = '' 
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
                 sh 'mvn clean test jacoco:report'
            }
        }
        stage ('SonarQube') {
            steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin123'

            }
        }
        stage('PUBLISH TO NEXUS') {
            steps {
                sh 'sh mvn deploy'
            }
        }
        stage('Building Docker Image') { 
            steps { 
                catchError { 
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
                }
            } 
        }
          stage('Deploy Docker Image') { 
            steps { 
                catchError { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                  }
                } 
            }
        }
        stage('Cleaning up') { 

            steps { 

                sh "docker rmi $registry:$BUILD_NUMBER" 

            }

        }
         stage('Docker compose ') {
            steps {
                sh ' docker-compose down '
                sh ' docker-compose up --force-recreate -d'
        }
    }
}
}
