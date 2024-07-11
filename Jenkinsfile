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

        // stage('Code coverage') {
        //     steps {
        //         sh 'mvn sonar:sonar'
        //     }
        // }

        stage('Versioning') {
            echo mvn -version
            echo java -version
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