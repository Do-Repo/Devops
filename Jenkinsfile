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