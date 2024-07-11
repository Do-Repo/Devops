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

        stage('Docker compose') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Maven Test') {
            steps {
                sh "mvn -version"
            }
        }

        stage('Unit testing') {
            steps {
                sh 'mvn test'
            }
        }
    }
}