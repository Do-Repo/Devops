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
                sh 'mvn package -DskipTests'
            }
        }

        stage('Unit testing') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube analysis') {
            tools {
                jdk "JDK17" // the name you have given the JDK installation using the JDK manager (Global Tool Configuration)
            }

            steps {
                withSonarQubeEnv(installationName: 'SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }


        stage('Building Image') {
            steps {
                sh 'docker build -t yasine123/eventsproject .'
            }
        }

        stage('Docker compose') {
            steps {
                sh 'docker compose up -d'
            }
        }

        
    }
}