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

        stage('Unit testing') {
            steps {
                sh 'docker compose exec app-backend mvn test -Dspring.profiles.active=test'
            }
        }
    }
}