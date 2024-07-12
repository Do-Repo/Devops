pipeline {

    agent any

    triggers {
        githubPush()
    }

    tools {
        maven "M2_HOME"
    }

    stages {

        stage('Checkout GIT') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -U'
            }
        }

        stage('Unit testing') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube analysis') {
            tools {
                jdk "JDK17" 
            }

            steps {
                withSonarQubeEnv(installationName: 'SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Nexus') {
            tools {
                jdk "JAVA_HOME"
            }
            steps {
                sh 'mvn clean deploy -DskipTests'
            }
        }


        stage('Building Image') {
            steps {
                sh 'docker build -t yasine123/eventsproject .'
            }
        }

        stage('Docker compose') {
            steps {
                sh 'docker compose down'
                sh 'docker compose up -d'
            }
        }

        stage('Deploy Image') {
            steps {
                sh 'docker login -u yasine123 -p yasine123'
                sh 'docker push yasine123/eventsproject '
            }
        }
    }
    
}