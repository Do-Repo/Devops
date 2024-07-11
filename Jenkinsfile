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

        stage('Build skip test') {
            steps {
                sh 'mvn package -DskipTests'
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

        stage('Unit testing') {
            steps {
                sh 'mvn test'
            }
        }
    }
}