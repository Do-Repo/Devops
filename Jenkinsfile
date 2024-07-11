pipeline {

    agent any

    triggers {
        githubPush()
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

        stage('Pause') {
            steps {
                sleep(time:30,unit:"SECONDS")
            }
        }

        stage('Unit testing') {
            steps {
                sh 'mvn test'
            }
        }
    }
}